package com.vladnickgo.Project.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Cruise {
    private final Integer id;
    private final String cruiseName;
    private final Route route;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final CruiseStatus cruiseStatus;
    private final Ship ship;

    private Cruise(Builder builder) {
        id = builder.id;
        cruiseName = builder.cruiseName;
        route = builder.route;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        cruiseStatus = builder.cruiseStatus;
        ship = builder.ship;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String cruiseName;
        private Route route;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private CruiseStatus cruiseStatus;
        private Ship ship;

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

        public Builder route(Route val) {
            route = val;
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

        public Builder cruiseStatus(CruiseStatus val) {
            cruiseStatus = val;
            return this;
        }

        public Builder ship(Ship val) {
            ship = val;
            return this;
        }

        public Cruise build() {
            return new Cruise(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public CruiseStatus getCruiseStatus() {
        return cruiseStatus;
    }

    public Ship getShip() {
        return ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cruise cruises = (Cruise) o;
        return Objects.equals(id, cruises.id) && Objects.equals(cruiseName, cruises.cruiseName) && Objects.equals(route, cruises.route) && Objects.equals(dateStart, cruises.dateStart) && Objects.equals(dateEnd, cruises.dateEnd) && Objects.equals(cruiseStatus, cruises.cruiseStatus) && Objects.equals(ship, cruises.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cruiseName, route, dateStart, dateEnd, cruiseStatus, ship);
    }

    @Override
    public String toString() {
        return "Cruises{" +
                "id=" + id +
                ", cruiseName='" + cruiseName + '\'' +
                ", route=" + route +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", cruiseStatus=" + cruiseStatus +
                ", ship=" + ship +
                '}';
    }
}
