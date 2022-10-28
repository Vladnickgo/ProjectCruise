package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class RoutePointDto {
    private final Integer id;
    private final String routeName;
    private final Integer dayNumber;
    private final String portNameUa;
    private final String portNameEn;
    private final String countryUa;
    private final String countryEn;

    private RoutePointDto(Builder builder) {
        id = builder.id;
        routeName = builder.routeName;
        dayNumber = builder.dayNumber;
        portNameUa = builder.portNameUa;
        portNameEn = builder.portNameEn;
        countryUa = builder.countryUa;
        countryEn = builder.countryEn;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String routeName;
        private Integer dayNumber;
        private String portNameUa;
        private String portNameEn;
        private String countryUa;
        private String countryEn;

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

        public Builder dayNumber(Integer val) {
            dayNumber = val;
            return this;
        }

        public Builder portNameUa(String val) {
            portNameUa = val;
            return this;
        }

        public Builder portNameEn(String val) {
            portNameEn = val;
            return this;
        }

        public Builder countryUa(String val) {
            countryUa = val;
            return this;
        }

        public Builder countryEn(String val) {
            countryEn = val;
            return this;
        }

        public RoutePointDto build() {
            return new RoutePointDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public String getPortNameUa() {
        return portNameUa;
    }

    public String getPortNameEn() {
        return portNameEn;
    }

    public String getCountryUa() {
        return countryUa;
    }

    public String getCountryEn() {
        return countryEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePointDto that = (RoutePointDto) o;
        return Objects.equals(id, that.id) && Objects.equals(routeName, that.routeName) && Objects.equals(dayNumber, that.dayNumber) && Objects.equals(portNameUa, that.portNameUa) && Objects.equals(portNameEn, that.portNameEn) && Objects.equals(countryUa, that.countryUa) && Objects.equals(countryEn, that.countryEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeName, dayNumber, portNameUa, portNameEn, countryUa, countryEn);
    }

    @Override
    public String toString() {
        return "RoutePointDto{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", dayNumber=" + dayNumber +
                ", portNameUa='" + portNameUa + '\'' +
                ", portNameEn='" + portNameEn + '\'' +
                ", countryUa='" + countryUa + '\'' +
                ", countryEn='" + countryEn + '\'' +
                '}';
    }
}
