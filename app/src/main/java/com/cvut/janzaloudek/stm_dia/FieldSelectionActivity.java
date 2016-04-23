package com.cvut.janzaloudek.stm_dia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.model.entity.Field;

public class FieldSelectionActivity extends AppCompatActivity {
    Field[] fields = {
            new Field("Benzinka", "Tohle je benzinka"),
            new Field("Pekarna", "Tohle je pekarna"),
            new Field("Supermarket", "Tohle je supermarket")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_selection);

        ArrayAdapter adapter = new ArrayAdapter<Field>(this, R.layout.activity_fields_listview_item, fields);

        ListView listView = (ListView) findViewById(R.id.fieldsListView);
        listView.setAdapter(adapter);
    }
}
