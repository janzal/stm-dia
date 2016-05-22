package com.cvut.janzaloudek.stm_dia.Survey;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * SurveyResponseOverviewAdapter is used for populating home screen responses list
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

        // TODO: Implement address retrieval from GPS
//        Geocoder geocoder;
//        List<Address> addresses = null;
//        geocoder = new Geocoder(, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(value.getLatitude(), value.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//        String city = addresses.get(0).getLocality();
//        String state = addresses.get(0).getAdminArea();
//        String country = addresses.get(0).getCountryName();
//        String postalCode = addresses.get(0).getPostalCode();
//        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        String dtStart = value.getPostedAt();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
            String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
            String day = (String) android.text.format.DateFormat.format("dd", date); //20

            ((TextView) result.findViewById(R.id.overview_survey_day)).setText(stringMonth);
            ((TextView) result.findViewById(R.id.overview_survey_day_name)).setText(dayOfTheWeek);
            ((TextView) result.findViewById(R.id.overview_survey_month)).setText(day);
        }

        return result;
    }
}
