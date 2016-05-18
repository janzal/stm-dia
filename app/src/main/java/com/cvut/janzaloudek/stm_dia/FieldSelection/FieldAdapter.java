package com.cvut.janzaloudek.stm_dia.FieldSelection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.MapAdapter;
import com.cvut.janzaloudek.stm_dia.R;
import com.cvut.janzaloudek.stm_dia.model.entity.Field;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by janzaloudek on 23/04/16.
 */
public class FieldAdapter extends MapAdapter<SurveyItem> {
    public FieldAdapter(Map<String, SurveyItem> map) {
        super(map);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fields_listview_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, SurveyItem> item = getItem(position);

        ((TextView) result.findViewById(R.id.field_title)).setText(item.getValue().getTitle());
        ((TextView) result.findViewById(R.id.field_content)).setText(item.getValue().getDescription());

        return result;
    }
}
