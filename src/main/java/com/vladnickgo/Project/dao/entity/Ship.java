package com.vladnickgo.Project.dao.entity;

import java.util.Objects;

public class Ship {
    private final Integer id;
    private final String shipName;
    private final Integer passengersCapacity;
    private final Integer numberOfStaff;
    private final String  shipImage;

    private Ship(Builder builder) {
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

        public Builder shipImageSource(String val) {
            shipImage = val;
            return this;
        }

        public Ship build() {
            return new Ship(this);
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
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id) && Objects.equals(shipName, ship.shipName) && Objects.equals(passengersCapacity, ship.passengersCapacity) && Objects.equals(numberOfStaff, ship.numberOfStaff) && Objects.equals(shipImage, ship.shipImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipName, passengersCapacity, numberOfStaff, shipImage);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", shipName='" + shipName + '\'' +
                ", passengersCapacity=" + passengersCapacity +
                ", numberOfStaff=" + numberOfStaff +
                ", shipImage='" + shipImage + '\'' +
                '}';
    }
}
