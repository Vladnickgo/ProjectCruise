package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class ShipDto {
    private final Integer id;
    private final String shipName;
    private final Integer passengersCapacity;
    private final Integer numberOfStaff;
    private final String  shipImage;

    private ShipDto(Builder builder) {
        id = builder.id;
        shipName = builder.shipName;
        passengersCapacity = builder.passengersCapacity;
        numberOfStaff = builder.numberOfStaff;
        shipImage = builder.shipImage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String shipName;
        private Integer passengersCapacity;
        private Integer numberOfStaff;
        private String shipImage;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder shipName(String val) {
            shipName = val;
            return this;
        }

        public Builder passengersCapacity(Integer val) {
            passengersCapacity = val;
            return this;
        }

        public Builder numberOfStaff(Integer val) {
            numberOfStaff = val;
            return this;
        }

        public Builder shipImage(String val) {
            shipImage = val;
            return this;
        }

        public ShipDto build() {
            return new ShipDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getShipName() {
        return shipName;
    }

    public Integer getPassengersCapacity() {
        return passengersCapacity;
    }

    public Integer getNumberOfStaff() {
        return numberOfStaff;
    }

    public String getShipImage() {
        return shipImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipDto shipDto = (ShipDto) o;
        return Objects.equals(id, shipDto.id) && Objects.equals(shipName, shipDto.shipName) && Objects.equals(passengersCapacity, shipDto.passengersCapacity) && Objects.equals(numberOfStaff, shipDto.numberOfStaff) && Objects.equals(shipImage, shipDto.shipImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipName, passengersCapacity, numberOfStaff, shipImage);
    }

    @Override
    public String toString() {
        return "ShipDto{" +
                "id=" + id +
                ", shipName='" + shipName + '\'' +
                ", passengersCapacity=" + passengersCapacity +
                ", numberOfStaff=" + numberOfStaff +
                ", shipImage='" + shipImage + '\'' +
                '}';
    }
}
