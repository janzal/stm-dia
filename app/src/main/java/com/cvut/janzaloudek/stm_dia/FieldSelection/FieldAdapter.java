package com.cvut.janzaloudek.stm_dia.FieldSelection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.R;
import com.cvut.janzaloudek.stm_dia.model.entity.Field;

import java.util.List;

/**
 * Created by janzaloudek on 23/04/16.
 */
public class FieldAdapter extends ArrayAdapter<Field> {

    public FieldAdapter(Context context, int resource, List<Field> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Field field = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_fields_listview_item, parent, false);
        }

        TextView header = (TextView) convertView.findViewById(R.id.field_title);
        TextView content = (TextView) convertView.findViewById(R.id.field_content);

        header.setText(field.getHeader());
        content.setText(field.getContent());

        return convertView;
    }
}
