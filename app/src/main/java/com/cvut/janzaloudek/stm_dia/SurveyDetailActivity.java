package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SurveyDetailActivity extends AppCompatActivity {
    static int record_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_survey_detail);

        Bundle b = getIntent().getExtras();
        record_id = b.getInt("rec_id");
        fillData(record_id);

    }

    private void fillData(int rec_id) {
        // TODO: here we will get records from db and place it on activity, on

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
