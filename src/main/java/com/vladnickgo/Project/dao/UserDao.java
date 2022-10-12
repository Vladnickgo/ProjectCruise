package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.dao.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User,Integer>{
    Optional<User> findByEmail(String email);
}
