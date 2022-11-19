package com.vladnickgo.Project.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CabinStatusDto {
    private final Integer id;
    private final Integer cabinId;
    private final LocalDate statusStart;
    private final LocalDate statusEnd;
    private final String statusStatementName;

    private CabinStatusDto(Builder builder) {
        id = builder.id;
        cabinId = builder.cabinId;
        statusStart = builder.statusStart;
        statusEnd = builder.statusEnd;
        statusStatementName = builder.statusStatementName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Integer cabinId;
        private LocalDate statusStart;
        private LocalDate statusEnd;
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

        public Builder statusStart(LocalDate val) {
            statusStart = val;
            return this;
        }

        public Builder statusEnd(LocalDate val) {
            statusEnd = val;
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

    public LocalDate getStatusStart() {
        return statusStart;
    }

    public LocalDate getStatusEnd() {
        return statusEnd;
    }

    public String getStatusStatementName() {
        return statusStatementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatusDto that = (CabinStatusDto) o;
        return Objects.equals(id, that.id) && Objects.equals(cabinId, that.cabinId) && Objects.equals(statusStart, that.statusStart) && Objects.equals(statusEnd, that.statusEnd) && Objects.equals(statusStatementName, that.statusStatementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabinId, statusStart, statusEnd, statusStatementName);
    }

    @Override
    public String toString() {
        return "CabinStatusDto{" +
                "id=" + id +
                ", cabinId=" + cabinId +
                ", statusStart=" + statusStart +
                ", statusEnd=" + statusEnd +
                ", statusStatementName='" + statusStatementName + '\'' +
                '}';
    }
}
