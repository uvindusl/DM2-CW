package com.app.Service;

import com.app.Entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
public class SupplierService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Supplier supplierLogin(String name, String password) {
        Supplier supplier = null;
        try {
            supplier = jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call LOGIN_SUPPLIER(?,?,?)}");

                cs.setString(1, name);
                cs.setString(2, password);

                cs.registerOutParameter(3, Types.NUMERIC);

                cs.execute();

                int id = cs.getInt(3);

                if (id > 0) {
                    return new Supplier(id);
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("error" , e);
        }
        return supplier;
    }

}
