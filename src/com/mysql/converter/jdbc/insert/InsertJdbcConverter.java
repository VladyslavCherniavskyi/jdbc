package com.mysql.converter.jdbc.insert;

import com.mysql.dto.InsertDataDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsertJdbcConverter {

    public InsertDataDto convert(Map<String, Object> rowData) {
        StringBuilder fieldsPart = new StringBuilder();
        StringBuilder valuesPart = new StringBuilder();
        List<Object> value = new ArrayList<>();

        rowData.forEach((key, values) -> {
            fieldsPart.append(key).append(",");
            valuesPart.append("?,");
            value.add(values);
        });

        fieldsPart.deleteCharAt(fieldsPart.length() - 1);
        valuesPart.deleteCharAt(valuesPart.length() - 1);

        return new InsertDataDto(fieldsPart.toString(), valuesPart.toString(), value);
    }
}
