package com.app.Service;

import com.app.Entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class CartService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cart getCartByCartId(int cartId){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call SELECT_CART_BY_CART_ID(?,?,?,?,?)}");

                //set input parameter
                cs.setInt(1, cartId);

                //register output parameters
                cs.registerOutParameter(2, Types.NUMERIC);
                cs.registerOutParameter(3, Types.NUMERIC);
                cs.registerOutParameter(4, Types.NUMERIC);
                cs.registerOutParameter(5, Types.NUMERIC);

                //Execute the stored procedure
                cs.execute();

                //get the output parameters
                int customerId = cs.getInt(2);
                int foodId = cs.getInt(3);
                int qty = cs.getInt(4);
                int subTotal = cs.getInt(5);

                //return the result
                return new Cart(customerId,foodId,qty,subTotal);

            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure", e);
        }
    }

    public Cart addToCart(Cart cart){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call INSERT_CART_DATA(?,?,?,?)}");

                //set input parameters
                cs.setInt(1, cart.getCustomerId());
                cs.setInt(2, cart.getFoodId());
                cs.setInt(3, cart.getQty());
                cs.setInt(4, cart.getSubTotal());

                //Execute the stored procedure
                cs.execute();

                //return the result
                return cart;

            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure", e);
        }
    }

    public Cart deleteCartByCustomerId(int customerId){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call DELETE_BY_CUSTOMER_ID(?)}");

                //set input parameters
                cs.setInt(1, customerId);

                //Execute the stored procedure
                cs.execute();

                //return the result
                return null;

            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure", e);
        }
    }

}
