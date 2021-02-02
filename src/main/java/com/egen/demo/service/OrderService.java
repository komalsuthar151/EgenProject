package com.egen.demo.service;

import com.egen.demo.Model.Order;
import com.egen.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderService {

    private final static Logger log =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        log.info("Running getAllOrders from OrderService");
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> findByOrderId(Long orderId) {
        log.info("Running getOrderById from OrderService with orderId " + orderId);
        return orderRepository.findById(orderId);
    }

    public Order findByCustomerName(String customerName) {
        log.info("Running getOrderById from OrderService with customerName " + customerName);
        return orderRepository.findByCustomerName(customerName);
    }

    public Order saveOrder(Order order) {
        log.info("Running createOrder from OrderService with order " + order.getCustomerId() + " & items " + order.getItems());
        setupOrder(order);
        return orderRepository.save(order);
    }

    public void deleteOrder(Optional<Order> order) {
        log.info("Running createOrder from OrderService with order " + order.get().getCustomerId() + " & items " + order.get().getItems());
        orderRepository.delete(order.get());
        return;
    }

    private Order setupOrder(Order order) {
        Order saveOrder = order;
        saveOrder.setCreatedDate(Date.from(Instant.now()));
        saveOrder.setStatus("RECEIVED");
        return order;
    }

    public void cancelOrder(Optional<Order> orderToBeCancelled) {
        log.info("Running cancelOrder from OrderService with order to be cancelled " + orderToBeCancelled.get().getOrderId() + " & items " + orderToBeCancelled.get().getItems());
        orderToBeCancelled.get().setStatus("Cancelled");
        orderRepository.save(orderToBeCancelled.get());
    }
}
