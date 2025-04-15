package com.app.Controller;

import com.app.Entity.Supplier;
import com.app.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/suppliers/login")
    private ResponseEntity<Supplier> supplierLogin(@RequestBody LoginRequest loginRequest) {
        Supplier supplier = supplierService.supplierLogin(loginRequest.getName(), loginRequest.getPassword());
        if(supplier != null){
            return ResponseEntity.ok(supplier);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping(path = "/suppliers")
    private Supplier registerSupplier(@RequestBody Supplier supplier){
        return supplierService.registerSupplier(supplier);
    }

    @GetMapping(path = "/suppliers")
    private List<Supplier> getAllSuppliers(){
        return supplierService.getSuppliers();
    }

    @GetMapping(path = "/suppliers/{supplierId}")
    private Supplier getSupplierByID(@PathVariable int supplierId){
        return supplierService.getSupplierById(supplierId);
    }

    @PutMapping(path = "/suppliers/{supplierId}")
    private Supplier updateSupplier(@PathVariable int supplierId, @RequestBody Supplier supplier){
        return supplierService.updateSupplier(supplierId, supplier);
    }

    @DeleteMapping(path = "/suppliers/{supplierId}")
    private ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId){
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }


    static class LoginRequest {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
