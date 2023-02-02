package com.mysql.repository;

import com.mysql.converter.jdbc.insert.InsertJdbcConverter;
import com.mysql.dto.InsertDataDto;
import com.mysql.exception.CloseException;
import com.mysql.exception.NotFoundException;
import com.mysql.repository.impl.DataSource;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class GenericRepository<E, I> implements Repository<E, I> {

    private final String tableName;
    private final String fieldId;
    private final DataSource dataSource;
    private final InsertJdbcConverter insertJdbcConverter;

    public GenericRepository(String tableName, String fieldId, DataSource dataSource, InsertJdbcConverter insertJdbcConverter) {
        this.tableName = tableName;
        this.fieldId = fieldId;
        this.dataSource = dataSource;
        this.insertJdbcConverter = insertJdbcConverter;
    }

    @Override
    public E get(I id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.connection();
            String getQuery = "select * from " + tableName + " where " + fieldId + " = ?";
            preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapTo(resultSet);
            } else {
                throw new NotFoundException(tableName + " with id " + id + " is not found");
            }
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public List<E> getAll() {
        String getAllQuery = "select * from " + tableName;
        try (Connection connection = dataSource.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllQuery)) {
            List<E> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapTo(resultSet));
            }
            return entities;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void remove(I id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String removeQuery = "delete from " + tableName + " where " + fieldId + " = ?";
            connection = dataSource.connection();
            preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setObject(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            if (rowDeleted == 0) {
                throw new NotFoundException(tableName + " with id " + id + " is not found");
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public E add(E entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.connection();

            Map<String, Object> rowData = mapFrom(entity);
            InsertDataDto insertData = insertJdbcConverter.convert(rowData);

            String addQuery = "insert into " + tableName + "(" + insertData.getFieldsPart() + ")" +
                    " values(" + insertData.getValuesPart() + ")";

            preparedStatement = connection.prepareStatement(addQuery, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < insertData.getValues().size(); i++) {
                preparedStatement.setObject(i + 1, insertData.getValues().get(i));
            }

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            I id = resultSet.getObject(1, getIdClass());

            return get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    protected Class<I> getIdClass() {
        return (Class<I>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    protected abstract E mapTo(ResultSet resultSet) throws SQLException;

    protected abstract Map<String, Object> mapFrom(E entity);

    private void close(ResultSet resultSet) {
        try {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

    private void close(Statement statement) {
        try {
            if (Objects.nonNull(statement)) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

    private void close(Connection connection) {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

}