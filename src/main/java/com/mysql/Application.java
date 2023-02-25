package com.mysql;

import com.mysql.config.DataSource;
import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.converter.jdbc.remove.RemoveJdbcConverter;
import com.mysql.converter.jdbc.update.UpdateJdbcConverter;
import com.mysql.entity.City;
import com.mysql.entity.Phone;
import com.mysql.exception.NotFoundException;
import com.mysql.repository.CityRepository;
import com.mysql.repository.PhoneRepository;
import com.mysql.repository.Repository;
import com.mysql.repository.impl.CityRepositoryImpl;
import com.mysql.repository.impl.PhoneRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        int id = 55;
        String idString = "7";
        List<String> ids = new ArrayList<>();
        ids.add("6");
        ids.add("8");

        List<Integer> idInteger = new ArrayList<>();
        idInteger.add(200);
        idInteger.add(201);

        List<Phone> phoneEntity = List.of(
                new Phone("6", "iPhoness"),
                new Phone("7", "iPhoness")
        );

        List<City> cityEntity = List.of(
                new City(201, "2012", "NLD", "d", 10),
                new City(202, "2022", "NLD", "d", 11)
        );

        DataSource dataSource = new DataSource();
        InsertJdbcConverter insertJdbcConverter = new InsertJdbcConverter();
        UpdateJdbcConverter updateJdbcConverter = new UpdateJdbcConverter();
        RemoveJdbcConverter removeJdbcConverter = new RemoveJdbcConverter();

        CityRepository cityRepository = new CityRepositoryImpl(dataSource, insertJdbcConverter, updateJdbcConverter, removeJdbcConverter);
        PhoneRepository phoneRepository = new PhoneRepositoryImpl(dataSource, insertJdbcConverter, updateJdbcConverter, removeJdbcConverter);

        City city = new City("aaa", "NLD", "d", 1);
        Phone phone = new Phone("16", "iPhone");

//        getAll(cityRepository);
//        getAll(phoneRepository);

//        add(cityRepository, city);
//        add(phoneRepository, phone);

//        get(cityRepository, id);
//        get(phoneRepository, idString);

//        remove(cityRepository, id);
//        remove(phoneRepository, idString);

//        edit(cityRepository, city);
//        edit(phoneRepository, phone);

//        removeAll(phoneRepository);
//        removeAll(phoneRepository, ids);
//        removeAll(cityRepository, idInteger);

//        editAll(phoneRepository,phoneEntity);
//        editAll(cityRepository,cityEntity);

//        addAll(phoneRepository,phoneEntity);
//        addAll(cityRepository,cityEntity);

//        System.out.println(ids);
//        getAll(phoneRepository);

//        get(cityRepository, 201);
//        get(cityRepository, 202);
    }

    private static <E, I> void add(Repository<E, I> repository, E entity) {
        E item = repository.add(entity);
        System.out.println(item);
    }

    private static <E, I> void get(Repository<E, I> repository, I id) {
        System.out.println(repository.get(id));
    }

    private static <E, I> void getAll(Repository<E, I> repository) {
        System.out.println(repository.getAll());
    }

    private static <E, I> void remove(Repository<E, I> repository, I id) {
        try {
            repository.remove(id);
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }

    private static <E, I> void edit(Repository<E, I> repository, E entity) {
        try {
            System.out.println(repository.edit(entity));
        } catch (NotFoundException | IllegalStateException ex) {
            ex.printStackTrace();
            System.out.println("failure update: " + ex.getMessage());
        }
    }

    private static <E, I> void removeAll(Repository<E, I> repository) {
        try {
            repository.removeAll();
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }

    private static <E, I> void removeAll(Repository<E, I> repository, List<I> ids) {
        try {
            repository.removeAll(ids);
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }

    private static <E, I> void editAll(Repository<E, I> repository, List<E> entity) {
        try {
            repository.editAll(entity);
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }

    private static <E, I> void addAll(Repository<E, I> repository, List<E> entity) {
        try {
            repository.addAll(entity);
            System.out.println("success");
        } catch (NotFoundException | IllegalStateException ex) {
            System.out.println("failure");
        }
    }
}
