package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.FieldSelection.FieldAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.Field;
import com.cvut.janzaloudek.stm_dia.model.entity.Survey;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FieldSelectionActivity extends AppCompatActivity {
    List<Field> fields = new ArrayList<Field>();
//    List<Field> fields = new ArrayList<Field>() {{
//            add(new Field(1, "Benzinka", "Tohle je benzinka"));
//            add(new Field(2, "Pekarna", "Pekarny jsou vsude v nasem okoli. Zapiste do deniku zkusenost s pekarnami ve vasem okoli."));
//            add(new Field(3, "Supermarket", "V supermarketu se nakupuje spousta zajimavych veci. Meli byste to vyzkouset a neco si tam koupit."));
//    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_selection);

        Firebase ref = new Firebase(Config.FIREBASE_SURVEYS_URL);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                Log.d()
                for (DataSnapshot surveySnapshot: snapshot.getChildren()) {
                    Survey survey = surveySnapshot.getValue(Survey.class);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ArrayAdapter adapter = new FieldAdapter(this, R.layout.activity_fields_listview_item, fields);

        ListView listView = (ListView) findViewById(R.id.fieldsListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FieldSelectionActivity.this, SurveyFormActivity.class);
                intent.putExtra("field", fields.get(i).id);
                startActivity(intent);
            }
        });
    }
}
