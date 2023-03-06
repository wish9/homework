package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.order.dto.OrderCoffeeResponseDto;
import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.dto.OrderResponseDto;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" )
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto){
        Order order = new Order();

        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());
        order.setMember(member);

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees()
                .stream().map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());

                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.setCoffee(coffee);

                    orderCoffee.setOrder(order);
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setOrderCoffees(orderCoffees);

        return order;

    };
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    OrderResponseDto orderToOrderResponseDto(Order order);
    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
    default OrderCoffeeResponseDto orderCoffeeToOrderCoffeeResponseDto(OrderCoffee orderCoffee) {
        Coffee coffee = orderCoffee.getCoffee();

        long coffeeId = coffee.getCoffeeId();
        String korName = coffee.getKorName();
        String engName = coffee.getEngName();
        Integer price = coffee.getPrice();

        OrderCoffeeResponseDto orderCoffeeResponseDto = new OrderCoffeeResponseDto(
                coffeeId, korName, engName, price, orderCoffee.getQuantity());

        return orderCoffeeResponseDto;
    }
}
