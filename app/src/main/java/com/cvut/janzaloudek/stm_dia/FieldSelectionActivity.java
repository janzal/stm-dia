package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.FieldSelection.FieldAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.Field;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FieldSelectionActivity extends AppCompatActivity {
    Map<String, SurveyItem> surveys = new LinkedHashMap<String, SurveyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_selection);

        Firebase ref = new Firebase(Config.FIREBASE_SURVEYS_URL);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    surveys.put(s.getKey(), s.getValue(SurveyItem.class));
                }

                FieldAdapter adapter = new FieldAdapter(surveys);

                ListView listView = (ListView) findViewById(R.id.fieldsListView);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
                    Intent intent = new Intent(FieldSelectionActivity.this, SurveyFormActivity.class);
                    intent.putExtra("field", ((Map.Entry<String, SurveyItem>)listView.getItemAtPosition(i)).getKey());
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
