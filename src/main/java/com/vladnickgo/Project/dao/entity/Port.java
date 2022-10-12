package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class Port {
    private final Integer id;
    private final String portNameUa;
    private final String portNameEn;
    private final String countryUa;
    private final String getCountryEn;

    private Port(Builder builder) {
        id = builder.id;
        portNameUa = builder.portNameUa;
        portNameEn = builder.portNameEn;
        countryUa = builder.countryUa;
        getCountryEn = builder.getCountryEn;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String portNameUa;
        private String portNameEn;
        private String countryUa;
        private String getCountryEn;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
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

        public Builder getCountryEn(String val) {
            getCountryEn = val;
            return this;
        }

        public Port build() {
            return new Port(this);
        }
    }

    public Integer getId() {
        return id;
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

    public String getGetCountryEn() {
        return getCountryEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id) && Objects.equals(portNameUa, port.portNameUa) && Objects.equals(portNameEn, port.portNameEn) && Objects.equals(countryUa, port.countryUa) && Objects.equals(getCountryEn, port.getCountryEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portNameUa, portNameEn, countryUa, getCountryEn);
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", portNameUa='" + portNameUa + '\'' +
                ", portNameEn='" + portNameEn + '\'' +
                ", countryUa='" + countryUa + '\'' +
                ", getCountryEn='" + getCountryEn + '\'' +
                '}';
    }
}
