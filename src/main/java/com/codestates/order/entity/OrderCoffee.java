package com.codestates.order.entity;

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_coffeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;
    @Column(nullable = false)
    private int quantity;
}
