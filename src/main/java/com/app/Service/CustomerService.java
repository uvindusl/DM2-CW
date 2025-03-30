package com.app.Service;

import com.app.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer getCustomerById(String customerId){

        try {
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)-> {
                CallableStatement cs = conn.prepareCall("{call get_customer_details(?,?,?,?)}");

               //set input parameters
               cs.setString(1,customerId);

                //register outputParameter
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.registerOutParameter(4, Types.INTEGER);

                //Execute the stored procedure
                cs.execute();

                //get the output parameter
                String customerName = cs.getString(2);
                String customerAddress = cs.getString(3);
                int customerTel = cs.getInt(4);

                //return the result
                return new Customer(customerName,customerAddress,customerTel);
            });
        }
        catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure",e);

        }

    }
}
