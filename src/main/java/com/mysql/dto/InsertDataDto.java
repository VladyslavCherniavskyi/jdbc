package com.mysql.dto;

import java.util.List;

public class InsertDataDto {

    private String fieldsPart;
    private String valuesPart;
    private List<Object> values;

    public InsertDataDto(String fieldsPart, String valuesPart, List<Object> values) {
        this.fieldsPart = fieldsPart;
        this.valuesPart = valuesPart;
        this.values = values;
    }

    public String getFieldsPart() {
        return fieldsPart;
    }

    public void setFieldsPart(String fieldsPart) {
        this.fieldsPart = fieldsPart;
    }

    public String getValuesPart() {
        return valuesPart;
    }

    public void setValuesPart(String valuesPart) {
        this.valuesPart = valuesPart;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
