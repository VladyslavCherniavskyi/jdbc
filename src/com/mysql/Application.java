package com.mysql;

import com.mysql.entiti.City;
import com.mysql.repository.Repository;
import com.mysql.repository.impl.CityRepository;

public class Application {
    public static void main(String[] args) {

        Repository<City, Integer> cityRepository = new CityRepository();

        System.out.println(cityRepository.get(0));

//        System.out.println(cityRepository.getAll());
    }
}
