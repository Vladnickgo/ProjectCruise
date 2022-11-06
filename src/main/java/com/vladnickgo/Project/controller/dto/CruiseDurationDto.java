package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CruiseDurationDto {
    private final String bottomDuration;
    private final String topDuration;
    private final Integer minDurationValue;
    private final Integer maxDurationValue;

    private CruiseDurationDto(Builder builder) {
        bottomDuration = builder.bottomDuration;
        topDuration = builder.topDuration;
        minDurationValue = builder.minDurationValue;
        maxDurationValue = builder.maxDurationValue;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String bottomDuration;
        private String topDuration;
        private Integer minDurationValue;
        private Integer maxDurationValue;

        private Builder() {
        }

        public Builder bottomDuration(String val) {
            bottomDuration = val;
            return this;
        }

        public Builder topDuration(String val) {
            topDuration = val;
            return this;
        }

        public Builder minDurationValue(Integer val) {
            minDurationValue = val;
            return this;
        }

        public Builder maxDurationValue(Integer val) {
            maxDurationValue = val;
            return this;
        }

        public CruiseDurationDto build() {
            return new CruiseDurationDto(this);
        }
    }

    public String getBottomDuration() {
        return bottomDuration;
    }

    public String getTopDuration() {
        return topDuration;
    }

    public Integer getMinDurationValue() {
        return minDurationValue;
    }

    public Integer getMaxDurationValue() {
        return maxDurationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseDurationDto that = (CruiseDurationDto) o;
        return Objects.equals(bottomDuration, that.bottomDuration) && Objects.equals(topDuration, that.topDuration) && Objects.equals(minDurationValue, that.minDurationValue) && Objects.equals(maxDurationValue, that.maxDurationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottomDuration, topDuration, minDurationValue, maxDurationValue);
    }

    @Override
    public String toString() {
        return "CruiseDurationDto{" +
                "bottomDuration='" + bottomDuration + '\'' +
                ", topDuration='" + topDuration + '\'' +
                ", minDurationValue=" + minDurationValue +
                ", maxDurationValue=" + maxDurationValue +
                '}';
    }
}
