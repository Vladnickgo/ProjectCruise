package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CabinStatusDto {
    private final Integer id;
    private final Integer cabinId;
    private final LocalDate status_start;
    private final LocalDate status_end;
    private final String statusStatementName;

    private CabinStatusDto(Builder builder) {
        id = builder.id;
        cabinId = builder.cabinId;
        status_start = builder.status_start;
        status_end = builder.status_end;
        statusStatementName = builder.statusStatementName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Integer cabinId;
        private LocalDate status_start;
        private LocalDate status_end;
        private String statusStatementName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabinId(Integer val) {
            cabinId = val;
            return this;
        }

        public Builder status_start(LocalDate val) {
            status_start = val;
            return this;
        }

        public Builder status_end(LocalDate val) {
            status_end = val;
            return this;
        }

        public Builder statusStatementName(String val) {
            statusStatementName = val;
            return this;
        }

        public CabinStatusDto build() {
            return new CabinStatusDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public LocalDate getStatus_start() {
        return status_start;
    }

    public LocalDate getStatus_end() {
        return status_end;
    }

    public String getStatusStatementName() {
        return statusStatementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatusDto that = (CabinStatusDto) o;
        return Objects.equals(id, that.id) && Objects.equals(cabinId, that.cabinId) && Objects.equals(status_start, that.status_start) && Objects.equals(status_end, that.status_end) && Objects.equals(statusStatementName, that.statusStatementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinId, status_start, status_end, statusStatementName);
    }

    @Override
    public String toString() {
        return "CabinStatusDto{" +
                "id=" + id +
                ", cabinId=" + cabinId +
                ", status_start=" + status_start +
                ", status_end=" + status_end +
                ", statusStatementName='" + statusStatementName + '\'' +
                '}';
    }
}
