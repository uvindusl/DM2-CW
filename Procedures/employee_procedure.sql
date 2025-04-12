-----select one----------------------------------------------
CREATE OR REPLACE PROCEDURE get_employee_details(
    p_employee_id IN NUMBER,
    p_employee_name OUT VARCHAR2,
    p_employee_address OUT VARCHAR2,
    p_employee_tel OUT NUMBER,
    p_employee_password OUT VARCHAR2
)
IS
BEGIN
    SELECT
        EMPLOYEE_NAME,
        EMPLOYEE_ADDRESS,
        EMPLOYEE_TEL,
        EMPLOYEE_PASSWORD
    INTO        
        p_employee_name,
        p_employee_address,
        p_employee_tel,
        p_employee_password
        
    FROM
        EMPLOYEE_TABLE
    WHERE
        EMPLOYEE_ID = p_employee_id;
END get_employee_details;   

-------select all-----------------------------------------------
CREATE or REPLACE PROCEDURE get_all_employees(p_employees OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN  p_employees FOR
    SELECT * FROM EMPLOYEE_TABLE;
END;
------------insert----------------------------------------------
CREATE OR REPLACE PROCEDURE create_employee(
    p_employee_name IN VARCHAR2,
    p_employee_address IN VARCHAR2,
    p_employee_tel IN NUMBER,
    p_employee_password IN VARCHAR2
    )
IS
BEGIN
    INSERT INTO employee_table(
        EMPLOYEE_NAME,
        EMPLOYEE_ADDRESS,
        EMPLOYEE_TEL,
        EMPLOYEE_PASSWORD
        )
    VALUES(
        p_employee_name,
        p_employee_address,
        p_employee_tel,
        p_employee_password
        );
    END create_employee;
---------------update---------------------
CREATE OR REPLACE PROCEDURE update_employee(
    p_employee_id IN NUMBER,
    p_employee_name IN VARCHAR2,
    p_employee_address IN VARCHAR2,
    p_employee_tel IN NUMBER,
    p_employee_password IN VARCHAR2
    )
IS
BEGIN
    UPDATE employee_table
    SET
    employee_name = p_employee_name,
    employee_address = p_employee_address,
    employee_tel = p_employee_tel,
    employee_password = P_employee_password
    WHERE
    employee_id = p_employee_id;
END update_employee;

--------------delete-----------------------------------------
CREATE OR REPLACE PROCEDURE delete_employee(p_employee_id IN NUMBER)
IS
BEGIN
    DELETE FROM employee_table
    WHERE employee_id = p_employee_id;
END delete_employee;