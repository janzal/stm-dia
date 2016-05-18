package com.cvut.janzaloudek.stm_dia.Survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.MapAdapter;
import com.cvut.janzaloudek.stm_dia.R;
import com.cvut.janzaloudek.stm_dia.model.entity.Question;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by janzaloudek on 25/04/16.
 */
public class SurveyResponseOverviewAdapter extends MapAdapter<SurveyResponse> {
    public SurveyResponseOverviewAdapter(Map<String, SurveyResponse> map) {
        super(map);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_question_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, SurveyResponse> item = getItem(position);
        SurveyResponse value = item.getValue();
        ((TextView) result.findViewById(R.id.overview_survey_name)).setText(value.getSurvey());

        String dtStart = value.getPostedAt();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
//            ((TextView) result.findViewById(R.id.overview_survey_day)).setText(cal.get(Calendar.DAY_OF_MONTH));
//            ((TextView) result.findViewById(R.id.overview_survey_day_name)).setText(cal.get(Calendar.DAY_OF_WEEK));
//            ((TextView) result.findViewById(R.id.overview_survey_month)).setText(cal.get(Calendar.DAY_OF_MONTH));
        }

        return result;
    }
}
