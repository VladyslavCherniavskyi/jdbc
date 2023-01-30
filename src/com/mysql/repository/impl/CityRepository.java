package com.mysql.repository.impl;

import com.mysql.entiti.City;
import com.mysql.repository.GenericRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRepository extends GenericRepository<City, Integer> {

    private static final String TABLE_NAME = "city";
    private static final String NAME_ID = "ID";

    public CityRepository() {
        super(TABLE_NAME, NAME_ID);
    }

    @Override
    protected City map(ResultSet resultSet) throws SQLException {

        Integer id = (Integer) resultSet.getObject("ID");
        String name = resultSet.getString("Name");
        String countryCode = resultSet.getString("CountryCode");
        String district = resultSet.getString("District");
        Integer population = (Integer) resultSet.getObject("Population");

        return new City(id, name, countryCode, district, population);
    }
}
