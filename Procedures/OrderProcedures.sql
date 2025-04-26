--get all orders
create or replace PROCEDURE GET_ALL_ORDERS(order_result OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN order_result FOR
    SELECT ORDER_ID , order_total_price, order_customer_id FROM ORDER_TABLE;
    EXCEPTION 
   WHEN no_data_found THEN 
      dbms_output.put_line('No such order!'); 

END GET_ALL_ORDERS;

--get all orders by customer id
create or replace PROCEDURE GET_ORDERS_BY_CUSTOMER_ID(CUSTOMER_ID IN NUMBER , order_result OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN order_result FOR
    SELECT ORDER_ID , order_total_price, order_customer_id FROM ORDER_TABLE
    WHERE ORDER_CUSTOMER_ID = CUSTOMER_ID;
    EXCEPTION 
   WHEN no_data_found THEN 
      dbms_output.put_line('No such order!'); 
END GET_ORDERS_BY_CUSTOMER_ID;

--get order by orderid
create or replace PROCEDURE GET_ORDER_BY_ID(ORDERID IN NUMBER , ORDER_TOTAL_PRICE OUT VARCHAR2 , ORDER_CUSTOMER_ID OUT NUMBER )
AS
BEGIN
    SELECT  order_total_price, order_customer_id
    INTO  ORDER_TOTAL_PRICE , ORDER_CUSTOMER_ID
    FROM ORDER_TABLE
    WHERE ORDER_ID = ORDERID;
    EXCEPTION 
   WHEN no_data_found THEN 
      dbms_output.put_line('No such order!'); 
END GET_ORDER_BY_ID;

--insert an order
create or replace PROCEDURE INSERT_ORDER_DATA (
    TOTAL_PRICE IN NUMBER,
    CUSTOMER_ID IN NUMBER,
    P_ORDER_ID OUT NUMBER
)
AS
BEGIN
    INSERT INTO ORDER_TABLE (ORDER_TOTAL_PRICE, ORDER_CUSTOMER_ID)
    VALUES (TOTAL_PRICE, CUSTOMER_ID)
    RETURNING ORDER_ID INTO P_ORDER_ID;
    EXCEPTION 
   WHEN no_data_found THEN 
      dbms_output.put_line('No such order!'); 
END INSERT_ORDER_DATA;

----update an order
--CREATE OR REPLACE PROCEDURE UPDATE_ORDER_DATA(ID IN NUMBER, STATUS IN VARCHAR2)
--IS
--BEGIN
--    UPDATE ORDER_TABLE
--    SET ORDER_STATUS = STATUS
--    WHERE ORDER_ID = ID;
--END UPDATE_ORDER_DATA;






