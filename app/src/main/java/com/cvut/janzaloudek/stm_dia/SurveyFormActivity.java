package com.cvut.janzaloudek.stm_dia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.Survey.QuestionAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SurveyFormActivity extends AppCompatActivity {
    List questions = new ArrayList(){{
        add(new Question(1, "Libilo se vam ovoce?"));
        add(new Question(2, "Libila se vam zelenina?"));
        add(new Question(3, "Bylo maso cerstve?"));
        add(new Question(4, "Jelita byla zalita kremovou omackou?"));
        add(new Question(5, "Kvalita obsluhy na stupnici 1-10?"));
        add(new Question(6, "Veprove koleno na smetane bylo uchazejici. Toto tvrzeni ohodnotte."));
        add(new Question(7, "Jelita byla zalita kremovou omackou?"));
        add(new Question(8, "Jelita byla zalita kremovou omackou?"));
    }};

    Location responseLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);

        ArrayAdapter adapter = new QuestionAdapter(this, R.layout.survey_form_question, questions);

        ListView listView = (ListView) findViewById(R.id.survey_form_questions);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(FieldSelectionActivity.this, SurveyFormActivity.class);
//                startActivity(intent);
//            }
//        });

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            responseLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.survey_form, menu);

        return true;
    }

    public void handleSaveSurvey(MenuItem item) {
        Intent intent = new Intent(this, DiaryOverview.class);
        startActivity(intent);
    }
}
