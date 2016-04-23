package com.cvut.janzaloudek.stm_dia.model.entity;

/**
 * Created by janzaloudek on 23/04/16.
 */
public abstract class HeaderContentEntry {
    protected String header, content;

    public HeaderContentEntry(String header, String content ) {
        this.header = header;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
