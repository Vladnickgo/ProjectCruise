package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CruiseDatesDto {
    private final String dateStart;
    private final String dateEnd;
    private final LocalDate minDateStart;
    private final LocalDate maxDateEnd;

    private CruiseDatesDto(Builder builder) {
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        minDateStart = builder.minDateStart;
        maxDateEnd = builder.maxDateEnd;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String dateStart;
        private String dateEnd;
        private LocalDate minDateStart;
        private LocalDate maxDateEnd;

        private Builder() {
        }

        public Builder dateStart(String val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(String val) {
            dateEnd = val;
            return this;
        }

        public Builder minDateStart(LocalDate val) {
            minDateStart = val;
            return this;
        }

        public Builder maxDateEnd(LocalDate val) {
            maxDateEnd = val;
            return this;
        }

        public CruiseDatesDto build() {
            return new CruiseDatesDto(this);
        }
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public LocalDate getMinDateStart() {
        return minDateStart;
    }

    public LocalDate getMaxDateEnd() {
        return maxDateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseDatesDto that = (CruiseDatesDto) o;
        return Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(minDateStart, that.minDateStart) && Objects.equals(maxDateEnd, that.maxDateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, minDateStart, maxDateEnd);
    }

    @Override
    public String toString() {
        return "CruiseDurationDto{" +
                "dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", minDateStart=" + minDateStart +
                ", maxDateEnd=" + maxDateEnd +
                '}';
    }
}
