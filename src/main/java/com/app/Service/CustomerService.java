package com.app.Service;

import com.app.Entity.Customer;
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
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer getCustomerById(int customerId){

        try {
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)-> {
                CallableStatement cs = conn.prepareCall("{call get_customer_details(?,?,?,?)}");

               //set input parameters
               cs.setInt(1,customerId);

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
                return new Customer(customerId,customerName,customerAddress,customerTel);
            });
        }
        catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public List<Customer> getAllCustomers(){
        try{
            //calling pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call get_all_customers(?)}");

                //register outputParameter
                cs.registerOutParameter(1,Types.REF_CURSOR);

                //Execute the stored procedure
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                List<Customer> customerList = new ArrayList<>();
                while (rs.next())
                {
                    customerList.add(new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("customer_name"),
                            rs.getString("customer_address"),
                            rs.getInt("customer_tel")
                    ));
                }
                return customerList;
            });

        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);
        }
    }

    public Customer createCustomer(Customer customer){

        try {
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)-> {
                CallableStatement cs = conn.prepareCall("{call create_customer(?,?,?)}");

                //set input parameters
                cs.setString(1, customer.getCustomerName());
                cs.setString(2, customer.getCustomerAddress());
                cs.setInt(3,customer.getCustomerTel());

                //Execute the stored procedure
                cs.execute();

                //return the result
                return customer;
            });
        }
        catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public Customer updateCustomer(int id,Customer customer){
        try{
            //calling pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call update_customer(?,?,?,?)}");

                //set input parameters
                cs.setInt(1,customer.getCustomerId());
                cs.setString(2, customer.getCustomerName());
                cs.setString(3, customer.getCustomerAddress());
                cs.setInt(4,customer.getCustomerTel());

                //Execute the stored procedure
                cs.execute();

                //return the result
                return customer;

            });

        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public Customer deleteCustomer(int id){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
            CallableStatement cs = conn.prepareCall("{call delete_customer(?)}");

            //set input parameters
            cs.setInt(1,id);

            //Execute the stored procedure
            cs.execute();

            //return the result
            return null;

        });

        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }
}
