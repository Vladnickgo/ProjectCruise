package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class CruiseStatus {
    private final Integer id;
    private final String cruiseStatusName;

    private CruiseStatus(Builder builder) {
        id = builder.id;
        cruiseStatusName = builder.cruiseStatusName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String cruiseStatusName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cruiseStatusName(String val) {
            cruiseStatusName = val;
            return this;
        }

        public CruiseStatus build() {
            return new CruiseStatus(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCruiseStatusName() {
        return cruiseStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseStatus that = (CruiseStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(cruiseStatusName, that.cruiseStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cruiseStatusName);
    }

    @Override
    public String toString() {
        return "CruiseStatus{" +
                "id=" + id +
                ", cruiseStatusName='" + cruiseStatusName + '\'' +
                '}';
    }
}
