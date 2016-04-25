package com.cvut.janzaloudek.stm_dia.model.entity;

/**
 * Created by janzaloudek on 24/04/16.
 */
public class Question {
    Integer id;
    Integer value;
    String content;

    public Question(Integer id, String content) {
        this(id, content, null);
    }

    public Question(Integer id, String content, Integer value) {
        this.id = id;
        this.content = content;
        this.value = value;
    }

    public void setValue(Integer value) {
        if (value > 10 || value < 0) return;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        return ((Question)o).id == id;
    }
}
