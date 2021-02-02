package com.egen.demo.Controller;

import com.egen.demo.Model.Item;
import com.egen.demo.Model.Order;
import com.egen.demo.repository.OrderRepository;
import com.egen.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;

    private final static Logger log =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/hello")
    public String hello() {
         return "Welcome page";
    }

    @GetMapping("/items/{orderId}")
    public List<Item> getItemsByOrderId(@PathVariable("orderId") Long orderId) {
        log.info("Getting Item from the Order");
        return itemService.findItemsByOrderId(orderId);
    }

    @Transactional
    @PostMapping("/items/add/{orderId}")
    public Item addItemToOrder(@PathVariable("orderId") Long orderId, @RequestBody Item item) {
        log.info("Running addItem method from ItemController");
        Optional<Order> order = orderRepository.findById(orderId);
        item.setOrder(order.get());
        return itemService.addItem(item);
    }


    @GetMapping("/items/search/{itemId}")
    public Item getItem(@PathVariable("itemId") Long itemId) throws Exception {
        log.info("Running getItem method from ItemController with itemId " + itemId);

        Optional<Item> optionalItem = itemService.findById(itemId);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            throw new Exception("Item not found with id " + itemId);
        }
    }

    @GetMapping("/items")
    public List<Item> findAllItems() {
        log.info("Running findAllItems method from ItemController");

        return itemService.getAllItems();
    }

    @GetMapping("/items/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") Long itemId) throws Exception {
        log.info("Running deleteOrder method from ItemController for itemId " + itemId);

        Optional<Item> itemToBeDeleted = itemService.findById(itemId);
        if (itemToBeDeleted.isPresent()) {
            itemService.deleteItem(itemToBeDeleted);
            return "Delete Successfully!";
        } else throw new Exception("Item not found with id " + itemId);
    }
}
