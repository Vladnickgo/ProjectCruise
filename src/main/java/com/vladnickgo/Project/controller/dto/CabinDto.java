package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinDto {
    private final Integer id;
    private final String cabinTypeName;
    private final String shipName;

    private CabinDto(Builder builder) {
        id = builder.id;
        cabinTypeName = builder.cabinTypeName;
        shipName = builder.shipName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String cabinTypeName;
        private String shipName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinTypeName(String val) {
            cabinTypeName = val;
            return this;
        }

        public Builder shipName(String val) {
            shipName = val;
            return this;
        }

        public CabinDto build() {
            return new CabinDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCabinTypeName() {
        return cabinTypeName;
    }

    public String getShipName() {
        return shipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinDto cabinDto = (CabinDto) o;
        return Objects.equals(id, cabinDto.id) && Objects.equals(cabinTypeName, cabinDto.cabinTypeName) && Objects.equals(shipName, cabinDto.shipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinTypeName, shipName);
    }

    @Override
    public String toString() {
        return "CabinDto{" +
                "id=" + id +
                ", cabinTypeName='" + cabinTypeName + '\'' +
                ", shipName='" + shipName + '\'' +
                '}';
    }
}
