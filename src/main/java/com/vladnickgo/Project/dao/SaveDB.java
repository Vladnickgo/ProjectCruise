package com.vladnickgo.Project.dao;

import com.vladnickgo.Project.connection.HikariConnectionPool;

public class SaveDB {
    private final HikariConnectionPool hikariConnectionPool=new HikariConnectionPool("bd-config");
}
