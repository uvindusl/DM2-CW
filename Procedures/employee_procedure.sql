CREATE OR REPLACE PROCEDURE get_employee_details(
    p_employee_id IN VARCHAR2,
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