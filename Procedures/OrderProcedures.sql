--get all orders--
CREATE OR REPLACE PROCEDURE GET_ALL_ORDERS(order_result OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN order_result FOR
    SELECT order_total_price, order_customer_id, order_status FROM ORDER_TABLE;

END GET_ALL_ORDERS;