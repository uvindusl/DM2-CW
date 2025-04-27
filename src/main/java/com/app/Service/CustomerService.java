package com.app.Service;

import com.app.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Customer createCustomer(Customer customer) {

        try {
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call create_customer(?,?,?,?)}");

                //set input parameters
                cs.setString(1, customer.getCustomerName());
                cs.setString(2, customer.getCustomerAddress());
                cs.setInt(3, customer.getCustomerTel());

                //register output parameter
                cs.registerOutParameter(4, Types.INTEGER);

                //Execute the stored procedure
                cs.execute();

                //get the output parameter
                int customerId = cs.getInt(4);

                //return the result
                return new Customer(customerId, customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerTel());
            });
        } catch (DataAccessException e) {
            throw new RuntimeException("Error executing stored procedure", e);
        }
    }

    public Customer updateCustomer(int customerId,Customer customer){
        try{
            //calling pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call update_customer(?,?,?,?)}");

                //set input parameters
                cs.setInt(1,customerId);
                cs.setString(2, customer.getCustomerName());
                cs.setString(3, customer.getCustomerAddress());
                cs.setInt(4,customer.getCustomerTel());

                //Execute the stored procedure
                cs.execute();

//                System.out.println("Updating customer with ID: " + customerId);
//                System.out.println("Customer details: " + customer);

                //return the result
                return customer;

            });

        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public Customer deleteCustomer(int customerId){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
            CallableStatement cs = conn.prepareCall("{call delete_customer(?)}");

            //set input parameters
            cs.setInt(1,customerId);

            //Execute the stored procedure
            cs.execute();

            //return the result
            return null;

        });

        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }

    }

//    public Customer loginCustomer(String customerName, int customerTel){
//        try{
//            //calling the pl/sql stored procedure
//            return jdbcTemplate.execute((Connection conn)->{
//                CallableStatement cs = conn.prepareCall("{call login_customer(?,?)}");
//
//                //set input parameters
//                cs.setString(1,customerName);
//                cs.setInt(2,customerTel);
//
//                //register outputParameter
//                cs.registerOutParameter(1, Types.VARCHAR);
//                cs.registerOutParameter(2,Types.INTEGER);
//
//                //Execute the stored procedure
//                cs.execute();
//
//                //get the output parameter
//                String name = cs.getString(1);
//                String tel = cs.getString(2);
//
//                System.out.println("check name: " + customerName);
//                System.out.println("check tels: " + customerTel);
//               // System.out.println("check result: " + result);
//
//                //return the result
//                return null;
//               // return loginCustomer(customerName,customerTel);
//
//            });
//
//        }catch (DataAccessException e){
//            throw new RuntimeException("Error executing stored procedure",e);
//
//        }
//
//    }

    public Customer loginCustomer(String customerName,int customerTel) {
        Customer customer = null;
        try {
            customer = jdbcTemplate.execute((Connection conn) -> {
                CallableStatement cs = conn.prepareCall("{call login_customer(?,?,?)}");

                cs.setString(1, customerName);
                cs.setInt(2, customerTel);

                cs.registerOutParameter(3, Types.NUMERIC);

                cs.execute();

                int id = cs.getInt(3);

                if (id > 0) {
                    return new Customer(id);
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("error" , e);
        }
        return customer;
    }
}
