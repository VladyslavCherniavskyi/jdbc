package com.mysql.converter.jdbc.remove;

import com.mysql.dto.RemoveDataDto;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveJdbcConverter {

    public <I> RemoveDataDto convert(List<I> rowData) {
        return new RemoveDataDto(rowData.stream()
                .map(key -> "?")
                .collect(Collectors.joining(", "))
        );
    }
}

