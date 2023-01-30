package com.mysql.repository;

import com.mysql.exception.CloseException;
import com.mysql.exception.NotFoundException;
import com.mysql.repository.impl.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GenericRepository<E, I> implements Repository<E, I> {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/world?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";
    private final String tableName;
    private final String nameId;

    public GenericRepository(String tableName, String nameId) {
        this.tableName = tableName;
        this.nameId = nameId;
    }

    @Override
    public E get(I id) {
        String getAllQuery = "select * from " + tableName + " where " + nameId + " = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(getAllQuery);
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
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
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

    protected abstract E mapTo(ResultSet resultSet) throws SQLException;

    private void close(ResultSet resultSet){
        try {
            if(Objects.nonNull(resultSet)) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

    private void close(Statement statement){
        try {
            if(Objects.nonNull(statement)) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

    private void close(Connection connection){
        try {
            if(Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new CloseException(ex);
        }
    }

}
