-----select one----------------------------------------------
CREATE OR REPLACE PROCEDURE get_customer_details(
    p_customer_id IN NUMBER,
    p_customer_name OUT VARCHAR2,
    p_customer_address OUT VARCHAR2,
    p_customer_tel OUT NUMBER
)
IS
BEGIN
    SELECT
        customer_NAME,
        customer_ADDRESS,
        customer_TEL
    INTO        
        p_customer_name,
        p_customer_address,
        p_customer_tel
        
    FROM
        CUSTOMER_TABLE
    WHERE
        CUSTOMER_ID = p_customer_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Customer do not exist');
END get_customer_details;       

------select all--------------------------------------------------
CREATE OR REPLACE PROCEDURE get_all_customers(p_customers OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN p_customers FOR
    SELECT * FROM CUSTOMER_TABLE; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Customers not found');
END;
-------insert----------------------------------------------------
CREATE OR REPLACE PROCEDURE create_customer(
    p_customer_name IN VARCHAR2,
    p_customer_address IN VARCHAR2,
    p_customer_tel IN NUMBER
    )
IS
BEGIN
    INSERT INTO customer_table (
        customer_NAME,
        customer_ADDRESS,
        customer_TEL
        )
    VALUES (
        p_customer_name,
        p_customer_address,
        p_customer_tel
        );
END create_customer;

--------update------------------------------------------------------
CREATE OR REPLACE PROCEDURE update_customer(
    p_customer_id IN NUMBER,
    p_customer_name IN VARCHAR2,
    p_customer_address IN VARCHAR2,
    p_customer_tel IN NUMBER
    )
IS
BEGIN
    UPDATE customer_table
    SET 
    customer_name = p_customer_name,
    customer_address = p_customer_address,
    customer_tel = p_customer_tel
    WHERE
    customer_id = p_customer_id;   
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Customer do not exist');
END;
--------delete----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE delete_customer(p_customer_id IN NUMBER)
IS
BEGIN
    DELETE FROM customer_table
    WHERE customer_id = p_customer_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Customer do not exist');
END delete_customer;

---------login---------------------------------------------------------------
CREATE OR REPLACE PROCEDURE login_customer(
    NAME IN VARCHAR2,
    TEL IN NUMBER,
    ID OUT NUMBER
)
IS
BEGIN
    SELECT customer_id
    INTO ID
    FROM customer_table
    WHERE customer_name = NAME AND customer_tel = TEL;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        dbms_output.put_line('Customer do not exist');
END;

    