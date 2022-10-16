package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class CabinStatusRequestDto {
    private final Integer cabinStatusId;
    private final Integer cabinId;
    private final CabinTypeDto cabinType;
    private final Integer numberOfRoom;

    private CabinStatusRequestDto(Builder builder) {
        cabinStatusId = builder.cabinStatusId;
        cabinId = builder.cabinId;
        cabinType = builder.cabinType;
        numberOfRoom = builder.numberOfRoom;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer cabinStatusId;
        private Integer cabinId;
        private CabinTypeDto cabinType;
        private Integer numberOfRoom;

        private Builder() {
        }

        public Builder cabinStatusId(Integer val) {
            cabinStatusId = val;
            return this;
        }

        public Builder cabinId(Integer val) {
            cabinId = val;
            return this;
        }

        public Builder cabinType(CabinTypeDto val) {
            cabinType = val;
            return this;
        }

        public Builder numberOfRoom(Integer val) {
            numberOfRoom = val;
            return this;
        }

        public CabinStatusRequestDto build() {
            return new CabinStatusRequestDto(this);
        }
    }

    public Integer getCabinStatusId() {
        return cabinStatusId;
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public CabinTypeDto getCabinType() {
        return cabinType;
    }

    public Integer getNumberOfRoom() {
        return numberOfRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabinStatusRequestDto that = (CabinStatusRequestDto) o;
        return Objects.equals(cabinStatusId, that.cabinStatusId) && Objects.equals(cabinId, that.cabinId) && Objects.equals(cabinType, that.cabinType) && Objects.equals(numberOfRoom, that.numberOfRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabinStatusId, cabinId, cabinType, numberOfRoom);
    }

    @Override
    public String toString() {
        return "CabinStatusRequestDto{" +
                "cabinStatusId=" + cabinStatusId +
                ", cabinId=" + cabinId +
                ", cabinType=" + cabinType +
                ", numberOfRoom=" + numberOfRoom +
                '}';
    }
}
