package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.dao.entity.*;

public class OrderMapper implements Mapper<OrderDto, Order>{
    @Override
    public Order mapDtoToEntity(OrderDto orderDto) {
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
        return OrderDto.newBuilder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .userDocuments(order.getUserDocuments())
                .cabinStatusId(order.getCabinStatus().getId())
                .orderDate(order.getOrderDate())
                .orderStatusId(order.getOrderStatus().getId())
                .cruiseId(order.getCruise().getId())
                .build();
    }
}
