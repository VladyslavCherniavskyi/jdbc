package com.mysql.dto;

public class RemoveDataDto {

    private String fieldsPart;

    public RemoveDataDto(String fieldsPart) {
        this.fieldsPart = fieldsPart;
    }

    public String getFieldsPart() {
        return fieldsPart;
    }

    public void setFieldsPart(String fieldsPart) {
        this.fieldsPart = fieldsPart;
    }
}
