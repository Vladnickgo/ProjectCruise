package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class CabinStatus {
    private final Integer id;
    private final String cabinStatusName;

    private CabinStatus(Builder builder) {
        id = builder.id;
        cabinStatusName = builder.cabinStatusName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String cabinStatusName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinStatusName(String val) {
            cabinStatusName = val;
            return this;
        }

        public CabinStatus build() {
            return new CabinStatus(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCabinStatusName() {
        return cabinStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatus that = (CabinStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(cabinStatusName, that.cabinStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinStatusName);
    }

    @Override
    public String toString() {
        return "CabinStatus{" +
                "id=" + id +
                ", cabinStatusName='" + cabinStatusName + '\'' +
                '}';
    }
}
