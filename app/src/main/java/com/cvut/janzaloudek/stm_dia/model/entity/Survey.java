package com.cvut.janzaloudek.stm_dia.model.entity;

import java.util.List;

/**
 * Created by janzaloudek on 24/04/16.
 */
public class Survey {
    List<Question> questions;

    public Survey(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
