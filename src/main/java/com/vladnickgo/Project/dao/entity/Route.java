package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class Route {
    private final Integer id;
    private final String routeName;

    private Route(Builder builder) {
        id = builder.id;
        routeName = builder.routeName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String routeName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder routeName(String val) {
            routeName = val;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(routeName, route.routeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeName);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                '}';
    }
}
