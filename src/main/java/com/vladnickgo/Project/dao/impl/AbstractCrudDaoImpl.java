package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.CrudDao;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E, Integer> {

    private static final Logger LOGGER = Logger.getLogger(AbstractCrudDaoImpl.class);

    private static final BiConsumer<PreparedStatement, String> STRING_CONSUMER = (PreparedStatement pr, String param) -> {
        try {
            pr.setString(1, param);
        } catch (SQLException e) {
            LOGGER.error("Connection is failed" + e);
            throw new DataBaseRuntimeException(e);
        }
    };

    private static final BiConsumer<PreparedStatement, Integer> INT_CONSUMER = (PreparedStatement pr, Integer param) -> {
        try {
            pr.setInt(1, param);
        } catch (SQLException e) {
            LOGGER.error("Connection is failed" + e);
            throw new DataBaseRuntimeException(e);
        }
    };

    protected final HikariConnectionPool connector;
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;

    protected AbstractCrudDaoImpl(HikariConnectionPool connector, String saveQuery, String findByIdQuery, String findAllQuery, String updateQuery) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
    }

    protected Optional<E> findByIntParam(Integer id, String query) {
        return findByParam(id, query, INT_CONSUMER);
    }

    protected Optional<E> findByStringParam(String param, String query) {
        return findByParam(param, query, STRING_CONSUMER);
    }

    private <P> Optional<E> findByParam(P param, String query, BiConsumer<PreparedStatement, P> consumer) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            consumer.accept(preparedStatement, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Connection is failed" + e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void save(E entity) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            mapForInsertStatement(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public void update(E entity) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            mapForUpdateStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Update is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<E> findAll() {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<E> entities = new HashSet<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return new ArrayList<>(entities);
            }
        } catch (SQLException e) {
            LOGGER.error("Connection is failed" + e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Optional<E> findById(Integer id) {
        return findByIntParam(id, findByIdQuery);
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void mapForInsertStatement(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void mapForUpdateStatement(PreparedStatement preparedStatement, E entity) throws SQLException;
}
