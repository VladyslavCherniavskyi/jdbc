package com.mysql.repository.impl;

import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.entity.City;
import com.mysql.repository.GenericRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CityRepository extends GenericRepository<City, Integer> {

    private static final String TABLE_NAME = "city";
    private static final String FIELD_ID = "ID";

    public CityRepository(DataSource dataSource, InsertJdbcConverter insertJdbcConverter) {
        super(TABLE_NAME, FIELD_ID, dataSource, insertJdbcConverter);
    }

    @Override
    protected City mapTo(ResultSet resultSet) throws SQLException {

        Integer id = (Integer) resultSet.getObject("ID");
        String name = resultSet.getString("Name");
        String countryCode = resultSet.getString("CountryCode");
        String district = resultSet.getString("District");
        Integer population = (Integer) resultSet.getObject("Population");

        return new City(id, name, countryCode, district, population);
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
