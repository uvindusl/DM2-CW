package com.app.Controller;

import com.app.Entity.SubOder;
import com.app.Service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class SubOrderController {

    @Autowired
    private SubOrderService subOrderService;

    @GetMapping(path = "/suborders/{orderId}")
    public List<SubOder> getBySubOrderId(@PathVariable Integer orderId){
        return subOrderService.getBySubOrderId(orderId);
    }

    @PostMapping(path = "/suborders")
    public SubOder addDataToSubOrder(@RequestBody SubOder subOder){
        return  subOrderService.addDataToSubOrder(subOder);
    }

    @GetMapping(path = "/suborders/supplier/{supplierId}")
    public List<SubOder> getBySupplierId(@PathVariable Integer supplierId){
        return subOrderService.getBySupplierId(supplierId);
    }

    @PatchMapping(path = "/suborders/{suborderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable int suborderId, @RequestBody Map<String, Object> map){
        String status = (String) map.get("status");
        subOrderService.updateStatus(suborderId, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/suborder/getMostSold")
    public List<SubOder> mostSoldProducts(){
        return subOrderService.mostSoldProducts();
    }
}
