package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CruiseRequestDto {
    private final String sorting;
    private final String ordering;
    private final String recordsOnPage;
    private final String numberOfPage;
    private final String statusAvailable;
    private final String statusInProgress;
    private final String statusFinished;
    private final String statusNotAvailable;

    private CruiseRequestDto(Builder builder) {
        sorting = builder.sorting;
        ordering = builder.ordering;
        recordsOnPage = builder.recordsOnPage;
        numberOfPage = builder.numberOfPage;
        statusAvailable = builder.statusAvailable;
        statusInProgress = builder.statusInProgress;
        statusFinished = builder.statusFinished;
        statusNotAvailable = builder.statusNotAvailable;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String sorting;
        private String ordering;
        private String recordsOnPage;
        private String numberOfPage;
        private String statusAvailable;
        private String statusInProgress;
        private String statusFinished;
        private String statusNotAvailable;

        private Builder() {
        }

        public Builder sorting(String val) {
            sorting = val;
            return this;
        }

        public Builder ordering(String val) {
            ordering = val;
            return this;
        }

        public Builder recordsOnPage(String val) {
            recordsOnPage = val;
            return this;
        }

        public Builder numberOfPage(String val) {
            numberOfPage = val;
            return this;
        }

        public Builder statusAvailable(String val) {
            statusAvailable = val;
            return this;
        }

        public Builder statusInProgress(String val) {
            statusInProgress = val;
            return this;
        }

        public Builder statusFinished(String val) {
            statusFinished = val;
            return this;
        }

        public Builder statusNotAvailable(String val) {
            statusNotAvailable = val;
            return this;
        }

        public CruiseRequestDto build() {
            return new CruiseRequestDto(this);
        }
    }

    public String getSorting() {
        return sorting;
    }

    public String getOrdering() {
        return ordering;
    }

    public String getRecordsOnPage() {
        return recordsOnPage;
    }

    public String getNumberOfPage() {
        return numberOfPage;
    }

    public String getStatusAvailable() {
        return statusAvailable;
    }

    public String getStatusInProgress() {
        return statusInProgress;
    }

    public String getStatusFinished() {
        return statusFinished;
    }

    public String getStatusNotAvailable() {
        return statusNotAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseRequestDto that = (CruiseRequestDto) o;
        return Objects.equals(sorting, that.sorting) && Objects.equals(ordering, that.ordering) && Objects.equals(recordsOnPage, that.recordsOnPage) && Objects.equals(numberOfPage, that.numberOfPage) && Objects.equals(statusAvailable, that.statusAvailable) && Objects.equals(statusInProgress, that.statusInProgress) && Objects.equals(statusFinished, that.statusFinished) && Objects.equals(statusNotAvailable, that.statusNotAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sorting, ordering, recordsOnPage, numberOfPage, statusAvailable, statusInProgress, statusFinished, statusNotAvailable);
    }

    @Override
    public String toString() {
        return "CruiseRequestDto{" +
                "sorting='" + sorting + '\'' +
                ", ordering='" + ordering + '\'' +
                ", recordsOnPage='" + recordsOnPage + '\'' +
                ", numberOfPage='" + numberOfPage + '\'' +
                ", statusAvailable='" + statusAvailable + '\'' +
                ", statusInProgress='" + statusInProgress + '\'' +
                ", statusFinished='" + statusFinished + '\'' +
                ", statusNotAvailable='" + statusNotAvailable + '\'' +
                '}';
    }
}
