package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CruiseResponseDto {
    private final Integer id;
    private final String cruiseName;
    private final String routeName;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final Integer nights;
    private final String cruiseStatusName;
    private final String shipName;
    private final String shipImageSource;

    private CruiseResponseDto(Builder builder) {
        id = builder.id;
        cruiseName = builder.cruiseName;
        routeName = builder.routeName;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        nights = builder.nights;
        cruiseStatusName = builder.cruiseStatusName;
        shipName = builder.shipName;
        shipImageSource = builder.shipImageSource;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String cruiseName;
        private String routeName;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private Integer nights;
        private String cruiseStatusName;
        private String shipName;
        private String shipImageSource;

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

        public Builder routeName(String val) {
            routeName = val;
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

        public Builder nights(Integer val) {
            nights = val;
            return this;
        }

        public Builder cruiseStatusName(String val) {
            cruiseStatusName = val;
            return this;
        }

        public Builder shipName(String val) {
            shipName = val;
            return this;
        }

        public Builder shipImageSource(String val) {
            shipImageSource = val;
            return this;
        }

        public CruiseResponseDto build() {
            return new CruiseResponseDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public String getRouteName() {
        return routeName;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Integer getNights() {
        return nights;
    }

    public String getCruiseStatusName() {
        return cruiseStatusName;
    }

    public String getShipName() {
        return shipName;
    }

    public String getShipImageSource() {
        return shipImageSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseResponseDto cruiseResponseDto = (CruiseResponseDto) o;
        return Objects.equals(id, cruiseResponseDto.id) && Objects.equals(cruiseName, cruiseResponseDto.cruiseName) && Objects.equals(routeName, cruiseResponseDto.routeName) && Objects.equals(dateStart, cruiseResponseDto.dateStart) && Objects.equals(dateEnd, cruiseResponseDto.dateEnd) && Objects.equals(nights, cruiseResponseDto.nights) && Objects.equals(cruiseStatusName, cruiseResponseDto.cruiseStatusName) && Objects.equals(shipName, cruiseResponseDto.shipName) && Objects.equals(shipImageSource, cruiseResponseDto.shipImageSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cruiseName, routeName, dateStart, dateEnd, nights, cruiseStatusName, shipName, shipImageSource);
    }

    @Override
    public String toString() {
        return "CruiseDto{" +
                "id=" + id +
                ", cruiseName='" + cruiseName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", nights=" + nights +
                ", cruiseStatusName='" + cruiseStatusName + '\'' +
                ", shipName='" + shipName + '\'' +
                ", shipImageSource='" + shipImageSource + '\'' +
                '}';
    }
}
