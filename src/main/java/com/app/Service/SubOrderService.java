package com.app.Service;

import com.app.Entity.SubOder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
public class SubOrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SubOder getByOrderId(int orderId){
        try {
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call SELECT_SUBORDER_BY_ORDER_ID(?,?,?,?)}");

                //set input parameter
                cs.setInt(1,orderId);

                //register output parameter
                cs.registerOutParameter(2, Types.NUMERIC);
                cs.registerOutParameter(3, Types.NUMERIC);
                cs.registerOutParameter(4, Types.NUMERIC);

                //execute the stored procedure
                cs.execute();

                //get the output parameter
                int customerId = cs.getInt(2);
                int foodId = cs.getInt(3);
                int qty = cs.getInt(4);

                //return the result
                return  new SubOder(customerId,foodId,qty,orderId);
            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excutng stored procedure", e);
        }
    }
}
