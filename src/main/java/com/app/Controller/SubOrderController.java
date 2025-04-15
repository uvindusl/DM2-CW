package com.app.Controller;

import com.app.Entity.SubOder;
import com.app.Service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class SubOrderController {

    @Autowired
    private SubOrderService subOrderService;

    @GetMapping(path = "/suborders/{orderId}")
    public SubOder getBySubOrderId(@PathVariable Integer orderId){
        return subOrderService.getBySubOrderId(orderId);
    }

    @PostMapping(path = "/suborders")
    public SubOder addDataToSubOrder(@RequestBody SubOder subOder){
        return  subOrderService.addDataToSubOrder(subOder);
    }
}
