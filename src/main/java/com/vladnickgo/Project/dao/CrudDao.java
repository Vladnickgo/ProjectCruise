package com.vladnickgo.Project.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDao<E, ID extends Serializable> {
    void save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

}