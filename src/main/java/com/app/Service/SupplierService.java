package com.app.Service;

import com.app.Entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

    public Supplier registerSupplier(Supplier supplier) {
        try {
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call create_supplier(?,?,?,?,?,?)}");

                cs.setString(1, supplier.getName());
                cs.setString(2, supplier.getAddress());
                cs.setInt(3, supplier.getTell());
                cs.setString(4, supplier.getCompany());
                cs.setString(5, supplier.getPassword());

                // Register the output parameter for the generated supplier_id
                cs.registerOutParameter(6, Types.NUMERIC);

                cs.execute();

                // Retrieve the generated supplier_id
                int generatedId = cs.getInt(6);
                supplier.setId(generatedId);

                return supplier;
            });
        } catch (DataAccessException e) {
            throw new RuntimeException("Error executing procedure", e);
        }
    }

    public List<Supplier> getSuppliers() {
        return jdbcTemplate.execute((Connection conn)->{
            CallableStatement cs = conn.prepareCall("{call get_all_suppliers(?)}");
            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            List<Supplier> supplierList = new ArrayList<>();
            while(rs.next()) {
                supplierList.add(new Supplier(
                        rs.getString("supplier_name"),
                        rs.getString("supplier_address"),
                        rs.getInt("supplier_tel"),
                        rs.getString("supplier_company"),
                        rs.getString("supplier_password")
                ));
            }
            return supplierList;

        });
    }

    public Supplier getSupplierById(int supplierId) {
        try{
            return jdbcTemplate.execute((Connection conn) ->{
                CallableStatement cs = conn.prepareCall("{call GET_SUPPLIER_BY_ID(?,?,?,?,?,?)}");

                cs.setInt(1, supplierId);

                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.registerOutParameter(4, Types.NUMERIC);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.registerOutParameter(6, Types.VARCHAR);

                cs.execute();

                String name = cs.getString(2);
                String address = cs.getString(3);
                int tell = cs.getInt(4);
                String company = cs.getString(5);
                String password = cs.getString(6);

                return new Supplier(name, address, tell, company, password);
            });

        }catch(DataAccessException e){
            throw new RuntimeException("Error executing stored procedure", e);
        }
    }

    public Supplier updateSupplier(int supplierId, Supplier supplier) {
        try {
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call UPDATE_SUPPLIER(?,?,?,?,?,?)}");


                cs.setInt(1, supplierId);
                cs.setString(2, supplier.getName());
                cs.setString(3, supplier.getAddress());
                cs.setInt(4, supplier.getTell());
                cs.setString(5, supplier.getCompany());
                cs.setString(6, supplier.getPassword());

                cs.execute();
                return supplier;
            });
        } catch (DataAccessException e) {
            throw new RuntimeException("Error executing procedure", e);
        }
    }

    public void deleteSupplier(int supplierId) {
        try{
            jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call DELETE_SUPPLIER(?)}");
                cs.setInt(1, supplierId);
                cs.execute();
                return null;
            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error ocured executing procedure", e);
        }
    }
}
