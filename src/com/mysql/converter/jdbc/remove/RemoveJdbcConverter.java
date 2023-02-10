package com.mysql.converter.jdbc.remove;

import com.mysql.dto.RemoveDataDto;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveJdbcConverter {

    public <I> RemoveDataDto convert(List<I> rowData) {
        StringBuilder fieldsPart = new StringBuilder();

        rowData.stream()
                .map(key -> "?,")
                .forEach(fieldsPart::append);

        return new RemoveDataDto(fieldsPart.toString());
    }

}

