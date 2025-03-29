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