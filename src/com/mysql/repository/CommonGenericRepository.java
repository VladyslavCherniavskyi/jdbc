package com.mysql.repository;

import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.config.DataSource;

import java.sql.Statement;

public abstract class CommonGenericRepository<E> extends GenericRepository<E, String> {

    public CommonGenericRepository(String tableName, String fieldId, DataSource dataSource, InsertJdbcConverter insertJdbcConverter) {
        super(tableName, fieldId, dataSource, insertJdbcConverter);
    }

    @Override
    protected int getAutoGeneratedKeys() {
        return Statement.NO_GENERATED_KEYS;
    }

    @Override
    protected void populateId(Statement statement, E entity) {
        System.out.println("Id is not needed to population into entity");
    }
}