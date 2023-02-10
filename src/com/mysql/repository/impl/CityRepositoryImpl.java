package com.mysql.repository.impl;

import com.mysql.config.DataSource;
import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.converter.jdbc.remove.RemoveJdbcConverter;
import com.mysql.converter.jdbc.update.UpdateJdbcConverter;
import com.mysql.entity.City;
import com.mysql.repository.CityRepository;
import com.mysql.repository.IntegerGenericRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CityRepositoryImpl extends IntegerGenericRepository<City> implements CityRepository {

    private static final String TABLE_NAME = "city";
    private static final String FIELD_ID = "id";

    public CityRepositoryImpl(DataSource dataSource,
                              InsertJdbcConverter insertJdbcConverter,
                              UpdateJdbcConverter updateJdbcConverter,
                              RemoveJdbcConverter removeJdbcConverter) {
        super(TABLE_NAME, FIELD_ID, dataSource, insertJdbcConverter, updateJdbcConverter, removeJdbcConverter);
    }

    @Override
    protected City mapTo(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getObject("id", Integer.class),
                resultSet.getString("name"),
                resultSet.getString("countryCode"),
                resultSet.getString("district"),
                resultSet.getObject("population", Integer.class)
        );
    }

    @Override
    protected Map<String, Object> mapFrom(City entity) {
        return Map.of(
                "name", entity.getName(),
                "countryCode", entity.getCountryCode(),
                "district", entity.getDistrict(),
                "population", entity.getPopulation()
        );
    }

    //TODO implement method
    @Override
    public Object getFullById(Integer id) {
        return null;
    }
}
