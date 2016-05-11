package com.cvut.janzaloudek.stm_dia.model.entity;

/**
 * Created by janzaloudek on 23/04/16.
 */
public class Field extends HeaderContentEntry {
    public int id;
    public Field(int id, String title, String description) {
        super(title, description);
        this.id = id;
    }
}
