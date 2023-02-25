package com.mysql.converter.jdbc.update;

import com.mysql.dto.UpdateDataDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateJdbcConverter {

    public UpdateDataDto convert(Map<String, Object> rowData) {

        StringBuilder fieldsPart = new StringBuilder();
        List<Object> value = new ArrayList<>();

        rowData.forEach((key, values) -> {
            fieldsPart.append(key).append(" = ?,");
            value.add(values);
        });

        fieldsPart.deleteCharAt(fieldsPart.length() - 1);

        return new UpdateDataDto(fieldsPart.toString(), value);
    }

}

