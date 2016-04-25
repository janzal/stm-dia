package com.cvut.janzaloudek.stm_dia.Survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.R;
import com.cvut.janzaloudek.stm_dia.model.entity.Question;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;

import java.util.List;

/**
 * Created by janzaloudek on 25/04/16.
 */
public class SurveyResponseOverviewAdapter extends ArrayAdapter<SurveyResponse> {

    public SurveyResponseOverviewAdapter(Context context, int resource, List<SurveyResponse> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SurveyResponse question = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.overview_question_item, parent, false);
        }

//        TextView content = (TextView) convertView.findViewById(R.id.question_text);
//        content.setText(question.getSurvey().field.getHeader());

        return convertView;
    }
}
