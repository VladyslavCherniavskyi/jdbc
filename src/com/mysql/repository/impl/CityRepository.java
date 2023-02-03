package com.mysql.repository.impl;

import com.mysql.config.DataSource;
import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.entity.City;
import com.mysql.repository.IntegerGenericRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CityRepository extends IntegerGenericRepository<City> {

    private static final String TABLE_NAME = "city";
    private static final String FIELD_ID = "ID";

    public CityRepository(DataSource dataSource, InsertJdbcConverter insertJdbcConverter) {
        super(TABLE_NAME, FIELD_ID, dataSource, insertJdbcConverter);
    }

    @Override
    protected City mapTo(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getObject("ID", Integer.class),
                resultSet.getString("Name"),
                resultSet.getString("CountryCode"),
                resultSet.getString("District"),
                resultSet.getObject("Population", Integer.class)
        );
    }

    @Override
    protected Map<String, Object> mapFrom(City entity) {
        return Map.of(
                "Name", entity.getName(),
                "CountryCode", entity.getCountryCode(),
                "District", entity.getDistrict(),
                "Population", entity.getPopulation()
        );
    }
}
