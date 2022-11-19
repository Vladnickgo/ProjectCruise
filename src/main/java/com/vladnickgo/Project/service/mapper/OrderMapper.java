package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.dao.entity.*;

import java.util.Optional;

public class OrderMapper implements Mapper<OrderDto, Order> {
    @Override
    public Order mapDtoToEntity(OrderDto orderDto) {
        if (orderDto == null) return null;
        return Order.newBuilder()
                .id(orderDto.getId())
                .user(User.newBuilder()
                        .id(orderDto.getUserId())
                        .build())
                .userDocuments(orderDto.getUserDocuments())
                .cabinStatus(CabinStatus.newBuilder()
                        .id(orderDto.getCabinStatusId())
                        .build())
                .orderDate(orderDto.getOrderDate())
                .orderStatus(OrderStatus.newBuilder()
                        .id(orderDto.getOrderStatusId())
                        .build())
                .cruise(Cruise.newBuilder()
                        .id(orderDto.getCruiseId())
                        .build())
                .build();
    }

    @Override
    public OrderDto mapEntityToDto(Order order) {
        if (order == null) return null;
        return OrderDto.newBuilder()
                .id(order.getId())
                .userId(getUserId(order))
                .userDocuments(order.getUserDocuments())
                .cabinStatusId(geCabinStatusId(order))
                .orderDate(order.getOrderDate())
                .orderStatusId(geOrderStatusId(order))
                .cruiseId(getCruiseId(order))
                .build();
    }

    private Integer getCruiseId(Order order) {
        return Optional.ofNullable(order.getCruise())
                .map(Cruise::getId)
                .orElse(null);
    }

    private Integer geOrderStatusId(Order order) {
        return Optional.ofNullable(order.getOrderStatus())
                .map(OrderStatus::getId)
                .orElse(null);
    }

    private Integer geCabinStatusId(Order order) {
        return Optional.ofNullable(order.getCabinStatus())
                .map(CabinStatus::getId)
                .orElse(null);
    }

    private Integer getUserId(Order order) {
        return Optional.ofNullable(order.getUser())
                .map(User::getId)
                .orElse(null);
    }
}
