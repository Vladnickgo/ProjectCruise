package com.vladnickgo.Project.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CabinStatus {
    private final Integer id;
    private final Cabin cabin;
    private final LocalDate statusStart;
    private final LocalDate statusEnd;
    private final CabinStatusStatement statusStatement;

    private CabinStatus(Builder builder) {
        id = builder.id;
        cabin = builder.cabin;
        statusStart = builder.statusStart;
        statusEnd = builder.statusEnd;
        statusStatement = builder.statusStatement;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Cabin cabin;
        private LocalDate statusStart;
        private LocalDate statusEnd;
        private CabinStatusStatement statusStatement;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cabin(Cabin val) {
            cabin = val;
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

        public Builder statusStatement(CabinStatusStatement val) {
            statusStatement = val;
            return this;
        }

        public CabinStatus build() {
            return new CabinStatus(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public LocalDate getStatusStart() {
        return statusStart;
    }

    public LocalDate getStatusEnd() {
        return statusEnd;
    }

    public CabinStatusStatement getStatusStatement() {
        return statusStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatus that = (CabinStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(cabin, that.cabin) && Objects.equals(statusStart, that.statusStart) && Objects.equals(statusEnd, that.statusEnd) && Objects.equals(statusStatement, that.statusStatement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cabin, statusStart, statusEnd, statusStatement);
    }

    @Override
    public String toString() {
        return "CabinStatus{" +
                "id=" + id +
                ", cabin=" + cabin +
                ", statusStart=" + statusStart +
                ", statusEnd=" + statusEnd +
                ", statusStatement=" + statusStatement +
                '}';
    }
}