package com.cvut.janzaloudek.stm_dia.model.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by janzaloudek on 25/04/16.
 */
public class SurveyResponse {
    Survey survey;
    HashMap<Question, Integer> responses;

    public SurveyResponse() {

    }

    public SurveyResponse(Survey survey, HashMap responses) {
        this.survey = survey;
        this.responses = responses;
    }

    public SurveyResponse(Survey survey) {
        this(survey, null);
    }

    public List<Question> getQuestions() {
        return survey.getQuestions();
    }

    public void setResponses(HashMap<Question, Integer> responses) {
        this.responses = responses;
    }

    public HashMap<Question, Integer> getResponses() {
        return responses;
    }

    public Survey getSurvey() {
        return survey;
    }
}
