package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateDto {
    private final LocalDate dateStart;
    private final LocalDate dateEnd;

    private LocalDateDto(Builder builder) {
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private LocalDate dateStart;
        private LocalDate dateEnd;

        private Builder() {
        }

        public Builder dateStart(LocalDate val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(LocalDate val) {
            dateEnd = val;
            return this;
        }

        public LocalDateDto build() {
            return new LocalDateDto(this);
        }
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalDateDto that = (LocalDateDto) o;
        return Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd);
    }

    @Override
    public String toString() {
        return "LocalDateDto{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
