package com.app.Service;

import com.app.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Order> getOrders() {
        return jdbcTemplate.execute((Connection conn) -> {
            CallableStatement cs = conn.prepareCall("{call get_all_orders(?)}");
            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);

            List<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("order_total_price"),
                        rs.getInt("order_customer_id")
                ));
            }
            return orderList;
        });
    }

    public Order createOrder(Order order) {
        try {
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call INSERT_ORDER_DATA(?,?,?)}");

                cs.registerOutParameter(4, Types.INTEGER);
                cs.setInt(1, order.getOrderTotalPrice());
                cs.setInt(2, order.getOrderCustomerId());

                cs.execute();

                int generatedId = cs.getInt(4);
                order.setId(generatedId);

                return order;
            });
        } catch (DataAccessException e) {
            throw new RuntimeException("Error executing procedure", e);
        }
    }

//    public void updateStatus(int orderId, String status) {
//        try {
//            jdbcTemplate.execute((Connection conn) -> {
//                CallableStatement cs = conn.prepareCall("{call UPDATE_ORDER_DATA(?,?)}");
//
//                cs.setInt(1, orderId);
//                cs.setString(2, status);
//
//                cs.execute();
//                return null;
//
//            });
//
//        } catch (DataAccessException e) {
//            throw new RuntimeException("Error executing stored procedure", e);
//
//        }
//
//    }
}

