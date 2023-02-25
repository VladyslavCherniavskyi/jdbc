package com.mysql.repository.impl;

import com.mysql.config.DataSource;
import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.converter.jdbc.remove.RemoveJdbcConverter;
import com.mysql.converter.jdbc.update.UpdateJdbcConverter;
import com.mysql.entity.Phone;
import com.mysql.repository.CommonGenericRepository;
import com.mysql.repository.PhoneRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class PhoneRepositoryImpl extends CommonGenericRepository<Phone, String> implements PhoneRepository {

    private static final String TABLE_NAME = "phone";
    private static final String FIELD_ID = "model";

    public PhoneRepositoryImpl(DataSource dataSource,
                               InsertJdbcConverter insertJdbcConverter,
                               UpdateJdbcConverter updateJdbcConverter,
                               RemoveJdbcConverter removeJdbcConverter) {
        super(TABLE_NAME, FIELD_ID, dataSource, insertJdbcConverter, updateJdbcConverter, removeJdbcConverter);
    }

    @Override
    protected Phone mapTo(ResultSet resultSet) throws SQLException {
        return new Phone(
                resultSet.getString("model"),
                resultSet.getString("brand")
        );
    }


    @Override
    protected Map<String, Object> mapFrom(Phone entity) {
        return Map.of(
                "model", entity.getModel(),
                "brand", entity.getBrand()
        );
    }

    @Override
    protected String getId(Phone entity) {
        if (Objects.isNull(entity.getModel())) {
            throw new IllegalStateException("Id is not found");
        }
        return entity.getModel();
    }
}
