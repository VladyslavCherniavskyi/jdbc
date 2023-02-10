package com.mysql.repository;

import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.config.DataSource;
import com.mysql.converter.jdbc.remove.RemoveJdbcConverter;
import com.mysql.converter.jdbc.update.UpdateJdbcConverter;

import java.sql.Statement;

public abstract class CommonGenericRepository<E, I> extends GenericRepository<E, I> {

    public CommonGenericRepository(
            String tableName,
            String fieldId,
            DataSource dataSource,
            InsertJdbcConverter insertJdbcConverter,
            UpdateJdbcConverter updateJdbcConverter,
            RemoveJdbcConverter removeJdbcConverter) {
        super(tableName, fieldId, dataSource, insertJdbcConverter, updateJdbcConverter, removeJdbcConverter);
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
