package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class RoutePoint {
    private final Integer id;
    private final Route route;
    private final Integer dayNumber;
    private final Port port;

    private RoutePoint(Builder builder) {
        id = builder.id;
        route = builder.route;
        dayNumber = builder.dayNumber;
        port = builder.port;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Route route;
        private Integer dayNumber;
        private Port port;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder route(Route val) {
            route = val;
            return this;
        }

        public Builder dayNumber(Integer val) {
            dayNumber = val;
            return this;
        }

        public Builder port(Port val) {
            port = val;
            return this;
        }

        public RoutePoint build() {
            return new RoutePoint(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public Port getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePoint that = (RoutePoint) o;
        return Objects.equals(id, that.id) && Objects.equals(route, that.route) && Objects.equals(dayNumber, that.dayNumber) && Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, dayNumber, port);
    }

    @Override
    public String toString() {
        return "RoutePoint{" +
                "id=" + id +
                ", route=" + route +
                ", dayNumber=" + dayNumber +
                ", port=" + port +
                '}';
    }
}
