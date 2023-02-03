package com.mysql;

import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.entity.City;
import com.mysql.exception.NotFoundException;
import com.mysql.repository.Repository;
import com.mysql.repository.impl.CityRepository;
import com.mysql.config.DataSource;

public class Application {
    public static void main(String[] args) {

        int id = 4501;

        DataSource dataSource = new DataSource();
        InsertJdbcConverter insertJdbcConverter = new InsertJdbcConverter();
        Repository<City, Integer> cityRepository = new CityRepository(dataSource, insertJdbcConverter);

        City city = new City("a", "NLD", "d", 10);

        getAll(cityRepository);
        add(cityRepository, city);
        get(cityRepository, id);
        remove(cityRepository, id);

    }

    private static void add(Repository<City, Integer> repository, City entity) {
        City city = repository.add(entity);
        System.out.println(city);
    }

    private static void get(Repository<City, Integer> repository, Integer id) {
        System.out.println(repository.get(id));
    }

    private static void getAll(Repository<City, Integer> repository) {
        System.out.println(repository.getAll());
    }

    private static void remove(Repository<City, Integer> repository, Integer id) {
        try {
            repository.remove(id);
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }

}
