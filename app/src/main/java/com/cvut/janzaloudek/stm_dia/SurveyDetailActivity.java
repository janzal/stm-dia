package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.Survey.QuestionAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class SurveyDetailActivity extends AppCompatActivity {
    Map<String, String> mQuestions = new LinkedHashMap<>();
    SurveyResponse SR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_survey_detail);

        Bundle b = getIntent().getExtras();
        SR = (SurveyResponse)b.getSerializable("rec");
        if (SR != null)
            loadData(SR.getSurvey());
    }

    private void loadData(String mField) {
        DatabaseReference ref_surv = FirebaseDatabase.getInstance().getReferenceFromUrl(Config.FIREBASE_SURVEYS_URL);
        ref_surv = ref_surv.child(mField).child("questions");

        ref_surv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    mQuestions.put(s.getKey(), s.getValue(String.class));
                }
                fillData(SR);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void setThemeAndDate(String survey, String dateTime) {
        RelativeLayout myLayout = (RelativeLayout) this.findViewById(R.id.survey_detail);
        if (myLayout != null) {
            TextView txtTheme = new TextView(this);
            Resources res = getResources();
            String textTheme = res.getString(R.string.theme, survey);
            txtTheme.setText(textTheme);
            txtTheme.setTextSize(20);
            RelativeLayout.LayoutParams lpl = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpl.setMargins(0, 15, 0, 30);
            txtTheme.setLayoutParams(lpl);
            myLayout.addView(txtTheme);

            TextView txtDateTime = new TextView(this);
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
            Date oneWayTripDate;
            try {
                oneWayTripDate = input.parse(dateTime);
                String textDateTime = res.getString(R.string.datum, output.format(oneWayTripDate));
                txtDateTime.setText(textDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtDateTime.setTextSize(20);
            RelativeLayout.LayoutParams lpr = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpr.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpr.setMargins(0, 15, 0, 30);
            txtDateTime.setLayoutParams(lpr);
            myLayout.addView(txtDateTime);
        }
    }

    private void placeQAToLayout(String curQuestion, Integer curIntAnswer, TableLayout curLayout) {
        if (curLayout != null) {
            TableRow rowHeaderQuest = new TableRow(curLayout.getContext());
            TextView txtQuest = new TextView(this);
            txtQuest.setText(curQuestion);
            txtQuest.setTextSize(16);
            txtQuest.setTypeface(null, Typeface.BOLD);
            TableRow.LayoutParams tr_quest = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            tr_quest.setMargins(5, 20, 0, 0);
            txtQuest.setLayoutParams(tr_quest);
            rowHeaderQuest.addView(txtQuest);
            curLayout.addView(rowHeaderQuest);

            // answers
            TableRow rowHeaderAns = new TableRow(curLayout.getContext());
            TextView txtAnswer = new TextView(this);
            if (curIntAnswer == null)
                txtAnswer.setText(R.string.not_answered);
            else
                txtAnswer.setText(String.format(Locale.ENGLISH,"%d",curIntAnswer));
            txtAnswer.setTextSize(16);
            txtAnswer.setTypeface(null, Typeface.ITALIC);
            txtAnswer.setTextColor(Color.parseColor("#960c0c"));
            txtAnswer.setGravity(Gravity.RIGHT);
            TableRow.LayoutParams tr_answer = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            tr_answer.setMargins(0, 10, 0, 0);
            txtAnswer.setLayoutParams(tr_answer);
            rowHeaderAns.addView(txtAnswer);
            curLayout.addView(rowHeaderAns);
        }
    }

    private void fillData(SurveyResponse surveyResp) {
        RelativeLayout myLayout = (RelativeLayout) this.findViewById(R.id.survey_detail);
        TableLayout table = (TableLayout)findViewById(R.id.list_qa);
        if (myLayout != null) {
            setThemeAndDate(surveyResp.getSurvey(), surveyResp.getPostedAt());
            Map<String, Integer> curAnswers = surveyResp.getResponses();
            for (String key : mQuestions.keySet()) {
                String curQuestion = mQuestions.get(key);
                Integer curIntAnswer = curAnswers.get(key);
                placeQAToLayout(curQuestion, curIntAnswer, table);
            }
        }
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent diaryIntent = new Intent(getApplicationContext(), DiaryOverview.class);
        startActivityForResult(diaryIntent, 0);
        return true;
    }
}
