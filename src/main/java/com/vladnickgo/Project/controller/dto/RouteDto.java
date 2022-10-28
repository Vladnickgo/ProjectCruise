package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class RouteDto {
    private final Integer id;
    private final String routeName;

    private RouteDto(Builder builder) {
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

        public RouteDto build() {
            return new RouteDto(this);
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
        RouteDto routeDto = (RouteDto) o;
        return Objects.equals(id, routeDto.id) && Objects.equals(routeName, routeDto.routeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeName);
    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                '}';
    }
}
