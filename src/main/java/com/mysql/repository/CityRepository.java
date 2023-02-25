package com.mysql.repository;

import com.mysql.entity.City;


public interface CityRepository extends Repository<City, Integer> {

    // TODO need to change output 'DTO'
    Object getFullById(Integer id);
}
