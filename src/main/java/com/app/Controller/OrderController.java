package com.app.Controller;

import com.app.Entity.Order;
import com.app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }


    @PostMapping(path = "/orders")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PatchMapping(path = "/orders/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable int orderId, @RequestBody Map<String, Object> map){
        String status = (String) map.get("orderstatus");
        orderService.updateStatus(orderId, status);
        return ResponseEntity.noContent().build();
    }
}
