package com.app.Controller;

import com.app.Entity.SubOder;
import com.app.Service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubOrderController {

    @Autowired
    private SubOrderService subOrderService;

    @GetMapping(path = "/suborders/{orderId}")
    public SubOder getBySubOrderId(@PathVariable Integer orderId){
        return subOrderService.getBySubOrderId(orderId);
    }
}
