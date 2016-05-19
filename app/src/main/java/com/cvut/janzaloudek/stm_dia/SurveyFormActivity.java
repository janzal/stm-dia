package com.cvut.janzaloudek.stm_dia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;

import com.cvut.janzaloudek.stm_dia.Survey.QuestionAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SurveyFormActivity extends AppCompatActivity {
    Map<String, String> mQuestions = new LinkedHashMap<String, String>();
    Location responseLocation;
    String mField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth ref = FirebaseAuth.getInstance();
        FirebaseUser authData = ref.getCurrentUser();

        if (authData == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_survey_form);

        Intent intent = getIntent();
        mField = intent.getStringExtra("field");

        loadData();
        receiveLocation();
    }

    private void receiveLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            responseLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
        }
    }

    private void loadData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(Config.FIREBASE_SURVEYS_URL);
        ref = ref.child(mField).child("questions");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    mQuestions.put(s.getKey(), s.getValue(String.class));
                }

                QuestionAdapter adapter = new QuestionAdapter(mQuestions);

                ListView listView = (ListView) findViewById(R.id.survey_form_questions);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.survey_form, menu);

        return true;
    }

    public void handleSaveSurvey(MenuItem item) {
        saveResponse();
        Intent intent = new Intent(this, DiaryOverview.class);
        startActivity(intent);
    }

    private void saveResponse() {
        SaveResponseTaskRunner runner = new SaveResponseTaskRunner();
        SurveyResponse response = new SurveyResponse();
        response.setSurvey(mField);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = new Date();
        response.setPostedAt(dateFormat.format(date));

        response.setLatitude(responseLocation.getLatitude());
        response.setLongitude(responseLocation.getLongitude());
        response.setAccuracy(responseLocation.getAccuracy());

        Map<String, Integer> responses = new LinkedHashMap<String, Integer>();
        ListView listView = (ListView) findViewById(R.id.survey_form_questions);
        for (int i = 0; i < listView.getChildCount(); i++) {
            QuestionAdapter adapter = (QuestionAdapter) listView.getAdapter();
            View listItemView = listView.getChildAt(i);
            int questionResponse = ((SeekBar) listItemView.findViewById(R.id.question_response)).getProgress();
            responses.put(adapter.getItem(i).getKey(), questionResponse);
        }
        response.setResponses(responses);

        runner.execute(response);
    }

    private class SaveResponseTaskRunner extends AsyncTask<SurveyResponse, String, String> {

        @Override
        protected String doInBackground(SurveyResponse... surveys) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(Config.FIREBASE_RESPONSES_URL);
            FirebaseAuth authRef = FirebaseAuth.getInstance();
            FirebaseUser user = authRef.getCurrentUser();

            DatabaseReference userRef = ref.child(user.getUid());
            userRef.push().setValue(surveys[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        }
    }
}
