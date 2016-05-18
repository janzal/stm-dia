package com.cvut.janzaloudek.stm_dia.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by janzaloudek on 24/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyItem {
    public String title;
    public String description;
    public Map<String, String> questions;

    public SurveyItem() {
    }

    public Map<String, String> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
