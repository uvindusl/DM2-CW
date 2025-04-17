package com.app.Service;

import com.app.Entity.SubOder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubOrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SubOder> getBySubOrderId(int orderId) {
        try {
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call SELECT_SUBORDER_BY_ORDER_ID(?,?)}");

                // Set input parameter
                cs.setInt(1, orderId);

                // Register output parameter (REF_CURSOR)
                cs.registerOutParameter(2, Types.REF_CURSOR);

                // Execute the stored procedure
                cs.execute();

                // Get the output parameter (ResultSet from the cursor)
                ResultSet rs = (ResultSet) cs.getObject(2);

                List<SubOder> subOderList = new ArrayList<>();
                while (rs.next()) {
                    subOderList.add(new SubOder(
                            rs.getInt("SUBORDER_ID"),
                            rs.getInt("SUBORDER_CUSTOMER_ID"),
                            rs.getInt("SUBORDER_FOOD_ID"),
                            rs.getInt("SUBORDER_QTY"),
                            rs.getInt("SUBORDER_ORDER_ID"), // Retrieve order ID from ResultSet
                            rs.getInt("SUBORDER_SUPPLIER_ID")
                    ));
                }
                return subOderList;
            });
        } catch (DataAccessException e) {
            throw new RuntimeException("Error executing stored procedure", e);
        }
    }

    public SubOder addDataToSubOrder(SubOder subOder){
        try{
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call INSERT_DATA_TO_SUBORDER(?,?,?,?,?)}");

                //set input parameter
                cs.setInt(1, subOder.getCustomerId());
                cs.setInt(2, subOder.getFoodId());
                cs.setInt(3, subOder.getQty());
                cs.setInt(4, subOder.getOrderId());
                cs.setInt(5,subOder.getSupplierId());

                //execute the stored procedure
                cs.execute();

                //return the result
                return subOder;
            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure", e);
        }
    }
}
