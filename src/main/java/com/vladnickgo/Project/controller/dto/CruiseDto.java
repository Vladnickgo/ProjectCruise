package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CruiseDto {
    private final Integer id;
    private final String cruiseName;
    private final Integer routeID;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final Integer cruiseStatusId;
    private final Integer shipId;
    private final Integer nights;

    private CruiseDto(Builder builder) {
        id = builder.id;
        cruiseName = builder.cruiseName;
        routeID = builder.routeID;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        cruiseStatusId = builder.cruiseStatusId;
        shipId = builder.shipId;
        nights = builder.nights;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String cruiseName;
        private Integer routeID;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private Integer cruiseStatusId;
        private Integer shipId;
        private Integer nights;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cruiseName(String val) {
            cruiseName = val;
            return this;
        }

        public Builder routeID(Integer val) {
            routeID = val;
            return this;
        }

        public Builder dateStart(LocalDate val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(LocalDate val) {
            dateEnd = val;
            return this;
        }

        public Builder cruiseStatusId(Integer val) {
            cruiseStatusId = val;
            return this;
        }

        public Builder shipId(Integer val) {
            shipId = val;
            return this;
        }

        public Builder nights(Integer val) {
            nights = val;
            return this;
        }

        public CruiseDto build() {
            return new CruiseDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public Integer getRouteID() {
        return routeID;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Integer getCruiseStatusId() {
        return cruiseStatusId;
    }

    public Integer getShipId() {
        return shipId;
    }

    public Integer getNights() {
        return nights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseDto cruiseDto = (CruiseDto) o;
        return Objects.equals(id, cruiseDto.id) && Objects.equals(cruiseName, cruiseDto.cruiseName) && Objects.equals(routeID, cruiseDto.routeID) && Objects.equals(dateStart, cruiseDto.dateStart) && Objects.equals(dateEnd, cruiseDto.dateEnd) && Objects.equals(cruiseStatusId, cruiseDto.cruiseStatusId) && Objects.equals(shipId, cruiseDto.shipId) && Objects.equals(nights, cruiseDto.nights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cruiseName, routeID, dateStart, dateEnd, cruiseStatusId, shipId, nights);
    }

    @Override
    public String toString() {
        return "CruiseDto{" +
                "id=" + id +
                ", cruiseName='" + cruiseName + '\'' +
                ", routeID=" + routeID +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", cruiseStatusId=" + cruiseStatusId +
                ", shipId=" + shipId +
                ", nights=" + nights +
                '}';
    }
}