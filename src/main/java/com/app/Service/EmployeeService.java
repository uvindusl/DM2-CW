package com.app.Service;

import com.app.Entity.Employee;

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
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee getEmployeeById(int employeeId){

        try{
            //calling the pl/sql stored procedure
            return  jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call get_employee_details(?,?,?,?,?)}");

                //set input parameters
                cs.setInt(1,employeeId);

                //register outputParameters
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.registerOutParameter(4, Types.INTEGER);
                cs.registerOutParameter(5, Types.VARCHAR);

                //Execute the stored procedure
                cs.execute();

                //get the output parameter
                String employeeName = cs.getString(2);
                String employeeAddress = cs.getString(3);
                int employeeTel =cs.getInt(4);
                String employeePassword = cs.getString(5);

                //return the result
                return new Employee(employeeId,employeeName,employeeAddress,employeeTel,employeePassword);
            });
        }
        catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public List<Employee> getAllEmployees(){
        try{
            //calling pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
               CallableStatement cs = conn.prepareCall("{call get_all_employees(?)}");

               //register outPutParameter
                cs.registerOutParameter(1,Types.REF_CURSOR);

                //Execute the procedure
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                List<Employee> employeeList = new ArrayList<>();
                while (rs.next())
                {
                    employeeList.add(new Employee(
                            rs.getInt("employee_id"),
                            rs.getString("employee_name"),
                            rs.getString("employee_address"),
                            rs.getInt("employee_tel"),
                            rs.getString("employee_password")
                    ));
                }
                return employeeList;
            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }
    }

    public Employee createEmployee(Employee employee){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)->{
                CallableStatement cs = conn.prepareCall("{call create_employee(?,?,?,?)}");

                //set input parameters
                cs.setString(1,employee.getEmployeeName());
                cs.setString(2,employee.getEmployeeAddress());
                cs.setInt(3,employee.getEmployeeTel());
                cs.setString(4, employee.getEmployeePassword());

                //Execute the stored procedure
                cs.execute();

                //return the result
                return employee;
            });
        } catch (DataAccessException e){
            throw new RuntimeException("Error executing stored procedure",e);

        }

    }
}
