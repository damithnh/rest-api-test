package test.restapi.controller;

import test.restapi.dto.OrderDto;
import test.restapi.model.Order;
import test.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/get")
    public List<Order> getOrders() {
        return orderService.getAllOrders(); // orders for the current logged in user
    }

    @RequestMapping(value = "/addtocart", method = RequestMethod.POST)
    public String addToCart(@RequestBody OrderDto orderDto) {
        return orderService.addToCart(orderDto);
    }
}
