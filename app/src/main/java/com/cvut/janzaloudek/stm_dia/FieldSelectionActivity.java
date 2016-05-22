package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.FieldSelection.FieldAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashMap;
import java.util.Map;

public class FieldSelectionActivity extends AppCompatActivity {
    Map<String, SurveyItem> surveys = new LinkedHashMap<String, SurveyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_selection);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(Config.FIREBASE_SURVEYS_URL);

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
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
