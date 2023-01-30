package com.mysql.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String getAllQuery = "select * from " + tableName + " where " + nameId + "= " + id;
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllQuery)) {
            while (resultSet.next()) {
                break;
            }
            return map(resultSet);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
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
                entities.add(map(resultSet));
            }
            return entities;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    protected abstract E map(ResultSet resultSet) throws SQLException;

}
