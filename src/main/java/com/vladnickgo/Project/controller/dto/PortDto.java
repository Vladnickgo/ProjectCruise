package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class PortDto {
    private final Integer id;
    private final String portNameUa;
    private final String portNameEn;
    private final String countryUa;
    private final String countryEn;

    private PortDto(Builder builder) {
        id = builder.id;
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

        public PortDto build() {
            return new PortDto(this);
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

    public String getCountryEn() {
        return countryEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortDto portDto = (PortDto) o;
        return Objects.equals(id, portDto.id) && Objects.equals(portNameUa, portDto.portNameUa) && Objects.equals(portNameEn, portDto.portNameEn) && Objects.equals(countryUa, portDto.countryUa) && Objects.equals(countryEn, portDto.countryEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portNameUa, portNameEn, countryUa, countryEn);
    }

    @Override
    public String toString() {
        return "PortDto{" +
                "id=" + id +
                ", portNameUa='" + portNameUa + '\'' +
                ", portNameEn='" + portNameEn + '\'' +
                ", countryUa='" + countryUa + '\'' +
                ", countryEn='" + countryEn + '\'' +
                '}';
    }
}

