package com.cvut.janzaloudek.stm_dia.model.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO
 *
 * Created by janzaloudek on 25/04/16.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyResponse implements Serializable {
    String postedAt;
    Map<String, Integer> responses;
    String survey;
    Integer timestamp;
    String locationName;
    double latitude, longitude;
    float accuracy;

    public SurveyResponse() {
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public Map<String, Integer> getResponses() {
        return responses;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public String getSurvey() {
        return survey;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    public void setResponses(Map<String, Integer> responses) {
        this.responses = responses;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
}
