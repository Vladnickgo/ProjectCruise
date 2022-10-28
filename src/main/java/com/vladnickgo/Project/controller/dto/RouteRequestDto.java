package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class RouteRequestDto {
    private final String numberOfPage;
    private final String recordsOnPage;

    private RouteRequestDto(Builder builder) {
        numberOfPage = builder.numberOfPage;
        recordsOnPage = builder.recordsOnPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String numberOfPage;
        private String recordsOnPage;

        private Builder() {
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public Builder recordsOnPage(String val) {
            recordsOnPage = val;
            return this;
        }

        public RouteRequestDto build() {
            return new RouteRequestDto(this);
        }
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    public String getRecordsOnPage() {
        return recordsOnPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteRequestDto that = (RouteRequestDto) o;
        return Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(recordsOnPage, that.recordsOnPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPage, recordsOnPage);
    }

    @Override
    public String toString() {
        return "RouteRequestDto{" +
                "numberOfPage='" + numberOfPage + '\'' +
                ", recordsOnPage='" + recordsOnPage + '\'' +
                '}';
    }
}
