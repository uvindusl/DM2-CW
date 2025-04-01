--1) ADD

CREATE OR REPLACE PROCEDURE add_food
(
    p_food_name IN VARCHAR2,
    p_food_description IN VARCHAR2,
    p_food_pic IN BLOB,
    p_food_price IN NUMBER,
    p_food_category IN VARCHAR2
)
AS
BEGIN
    INSERT INTO food_table (food_name, food_description, food_pic, food_price, food_category)
    VALUES (p_food_name, p_food_description, p_food_pic, p_food_price, p_food_category);
    COMMIT;
END;

--2) UPDATE

CREATE OR REPLACE PROCEDURE update_food
(
    p_food_id IN NUMBER,
    p_food_name IN VARCHAR2,
    p_food_description IN VARCHAR2,
    p_food_pic IN BLOB,
    p_food_price IN NUMBER,
    p_food_category IN VARCHAR2
)
AS
BEGIN
    UPDATE food_table
    SET 
        food_name = p_food_name,
        food_description = p_food_description,
        food_pic = p_food_pic,
        food_price = p_food_price,
        food_category = p_food_category
    WHERE food_id = p_food_id;
    COMMIT;
END;

--3) DELETE

CREATE OR REPLACE PROCEDURE delete_food
(
    p_food_id IN NUMBER
)
AS
BEGIN
    DELETE FROM food_table WHERE food_id = p_food_id;
    COMMIT;
END;

--4) ALL FOODS

CREATE OR REPLACE PROCEDURE get_all_foods
(
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR 
    SELECT * FROM food_table;
END;

--5) SEARCH - NAME
CREATE OR REPLACE PROCEDURE search_by_name
(
    p_name VARCHAR2,
    P_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR 
    SELECT * FROM food_table WHERE LOWER(food_name) LIKE '%' || LOWER(p_name) || '%';
END;

--6) SEARCH - PRICE

CREATE OR REPLACE PROCEDURE search_by_price
(
    p_min_price IN NUMBER,
    p_max_price IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT * FROM food_table WHERE food_price BETWEEN p_min_price AND p_max_price;
END;
    
--7) FIND BY ID

CREATE OR REPLACE PROCEDURE search_by_id
(
    p_food_id IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT * FROM food_table WHERE food_id = p_food_id;
END;

select * from food_table