package com.cvut.janzaloudek.stm_dia.Survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.R;
import com.cvut.janzaloudek.stm_dia.model.entity.Field;
import com.cvut.janzaloudek.stm_dia.model.entity.Question;

import java.util.List;

/**
 * Created by janzaloudek on 23/04/16.
 */
public class QuestionAdapter extends ArrayAdapter<Question> {

    public QuestionAdapter(Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.survey_form_question, parent, false);
        }

        TextView content = (TextView) convertView.findViewById(R.id.question_text);

        content.setText(question.getContent());

        return convertView;
    }
}
