-----select one----------------------------------------------
CREATE OR REPLACE PROCEDURE get_customer_details(
    p_customer_id IN VARCHAR2,
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
END get_customer_details;       

------select all--------------------------------------------------
CREATE OR REPLACE PROCEDURE get_all_customers(p_customers OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN p_customers FOR
    SELECT * FROM CUSTOMER_TABLE;
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
    