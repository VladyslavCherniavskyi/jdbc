package com.mysql.dto;

import java.util.List;

public class UpdateDataDto {

    private String fieldsPart;
    private List<Object> values;

    public UpdateDataDto(String fieldsPart, List<Object> values) {
        this.fieldsPart = fieldsPart;
        this.values = values;
    }

    public String getFieldsPart() {
        return fieldsPart;
    }

    public void setFieldsPart(String fieldsPart) {
        this.fieldsPart = fieldsPart;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
