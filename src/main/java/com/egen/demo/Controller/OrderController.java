package com.egen.demo.Controller;

import com.egen.demo.Model.Order;
import com.egen.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final static Logger log =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/")
    public String home() {
         return "Working page";
    }

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        log.info("Fetching all the orders");
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Optional<Order> findByOrderId(@PathVariable("orderId") Long orderId) throws Exception {
        log.info("Fetching order by orderId " + orderId);
        Optional<Order> order = orderService.findByOrderId(orderId);
        return order;
    }

    @PostMapping("/orders/create")
    public Order createOrder(@RequestBody Order order) {
        log.info("Creating order with orderId " + order.getOrderId());
        return orderService.saveOrder(order);
    }


    @GetMapping("/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long orderId) throws Exception {
        log.info("Cancel order with orderId " + orderId);

        Optional<Order> orderToBeCancelled = orderService.findByOrderId(orderId);
        if (orderToBeCancelled.isPresent()) {
            orderService.cancelOrder(orderToBeCancelled);
            return "Cancelled Successfully!";
        } else throw new Exception("Order not found with id " + orderId);
    }

    @GetMapping("/orders/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId) throws Exception {
        log.info("Deleting Order" + orderId);

        Optional<Order> orderToBeDeleted = orderService.findByOrderId(orderId);
        if (orderToBeDeleted.isPresent()) {
            orderService.deleteOrder(orderToBeDeleted);
            return "Delete Successfully!";
        } else throw new Exception("Order not found with id " + orderId);
    }
}
