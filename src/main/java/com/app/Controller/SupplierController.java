package com.app.Controller;

import com.app.Entity.Supplier;
import com.app.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
