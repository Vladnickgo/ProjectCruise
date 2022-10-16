package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class CabinStatusStatement {
    private final Integer id;
    private final String statusStatementName;

    private CabinStatusStatement(Builder builder) {
        id = builder.id;
        statusStatementName = builder.statusStatementName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String statusStatementName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder statusStatementName(String val) {
            statusStatementName = val;
            return this;
        }

        public CabinStatusStatement build() {
            return new CabinStatusStatement(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getStatusStatementName() {
        return statusStatementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatusStatement that = (CabinStatusStatement) o;
        return Objects.equals(id, that.id) && Objects.equals(statusStatementName, that.statusStatementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusStatementName);
    }

    @Override
    public String toString() {
        return "CabinStatusStatement{" +
                "id=" + id +
                ", statusStatementName='" + statusStatementName + '\'' +
                '}';
    }
}