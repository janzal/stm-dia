package com.cvut.janzaloudek.stm_dia.model.entity;

import java.util.List;

/**
 * Created by janzaloudek on 24/04/16.
 */
public class Survey {
    public List<Question> questions;
    public String title;
    public String description;

    public Survey() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
