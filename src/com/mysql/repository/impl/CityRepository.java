package com.mysql.repository.impl;

import com.mysql.entiti.City;
import com.mysql.repository.GenericRepository;

import java.sql.ResultSet;

public class CityRepository extends GenericRepository<City, Integer> {

    private static final String TABLE_NAME = "city";

    public CityRepository() {
        super(TABLE_NAME);
    }

    @Override
    protected City map(ResultSet resultSet) {
        return null;
    }
}
