package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class RoutePointRequestDto {
    private final Integer routePointId;
    private final Integer routeId;
    private final Integer dayNumber;
    private final Integer portId;

    private RoutePointRequestDto(Builder builder) {
        routePointId = builder.routePointId;
        routeId = builder.routeId;
        dayNumber = builder.dayNumber;
        portId = builder.portId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer routePointId;
        private Integer routeId;
        private Integer dayNumber;
        private Integer portId;

        private Builder() {
        }

        public Builder routePointId(Integer val) {
            routePointId = val;
            return this;
        }

        public Builder routeId(Integer val) {
            routeId = val;
            return this;
        }

        public Builder dayNumber(Integer val) {
            dayNumber = val;
            return this;
        }

        public Builder portId(Integer val) {
            portId = val;
            return this;
        }

        public RoutePointRequestDto build() {
            return new RoutePointRequestDto(this);
        }
    }

    public Integer getRoutePointId() {
        return routePointId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public Integer getPortId() {
        return portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePointRequestDto that = (RoutePointRequestDto) o;
        return Objects.equals(routePointId, that.routePointId) && Objects.equals(routeId, that.routeId) && Objects.equals(dayNumber, that.dayNumber) && Objects.equals(portId, that.portId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routePointId, routeId, dayNumber, portId);
    }

    @Override
    public String toString() {
        return "RoutePointRequestDto{" +
                "routePointId=" + routePointId +
                ", routeId=" + routeId +
                ", dayNumber=" + dayNumber +
                ", portId=" + portId +
                '}';
    }
}