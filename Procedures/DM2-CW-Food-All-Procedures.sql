-- 1) ADD
CREATE OR REPLACE PROCEDURE add_food
(
    p_food_name IN VARCHAR2,
    p_food_description IN VARCHAR2,
    p_food_pic IN BLOB,
    p_food_price IN NUMBER,
    p_food_category IN VARCHAR2,
    p_food_supplier_id IN NUMBER
)
AS
BEGIN
    INSERT INTO food_table (food_name, food_description, food_pic, food_price, food_category, food_supplier_id)
    VALUES (p_food_name, p_food_description, p_food_pic, p_food_price, p_food_category, p_food_supplier_id);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error adding food: ' || SQLERRM);
END;

-- 2) UPDATE
CREATE OR REPLACE PROCEDURE update_food
(
    p_food_id IN NUMBER,
    p_food_name IN VARCHAR2,
    p_food_description IN VARCHAR2,
    p_food_pic IN BLOB,
    p_food_price IN NUMBER,
    p_food_category IN VARCHAR2,
    p_food_supplier_id IN NUMBER
)
AS
BEGIN
    UPDATE food_table
    SET 
        food_name = p_food_name,
        food_description = p_food_description,
        food_pic = p_food_pic,
        food_price = p_food_price,
        food_category = p_food_category,
        food_supplier_id = p_food_supplier_id
    WHERE food_id = p_food_id;

EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error updating food: ' || SQLERRM);
END;

-- 3) DELETE
CREATE OR REPLACE PROCEDURE delete_food
(
    p_food_id IN NUMBER
)
AS
BEGIN
    DELETE FROM food_table WHERE food_id = p_food_id;

EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error deleting food: ' || SQLERRM);
END;

-- 4) GET ALL FOODS
CREATE OR REPLACE PROCEDURE get_all_foods
(
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR 
    SELECT * FROM food_table;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error fetching foods: ' || SQLERRM);
END;

-- 5) SEARCH BY NAME
CREATE OR REPLACE PROCEDURE search_by_name
(
    p_name IN VARCHAR2,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR 
    SELECT * FROM food_table 
    WHERE LOWER(food_name) LIKE '%' || LOWER(p_name) || '%';
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error searching food by name: ' || SQLERRM);
END;

-- 6) SEARCH BY PRICE RANGE
CREATE OR REPLACE PROCEDURE search_by_price
(
    p_min_price IN NUMBER,
    p_max_price IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT * FROM food_table 
    WHERE food_price BETWEEN p_min_price AND p_max_price;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error searching food by price: ' || SQLERRM);
END;
    
-- 7) SEARCH BY ID
CREATE OR REPLACE PROCEDURE search_by_id
(
    p_food_id IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT * FROM food_table 
    WHERE food_id = p_food_id;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error searching food by ID: ' || SQLERRM);
END;

-- 8) SEARCH BY SUPPLIER ID
CREATE OR REPLACE PROCEDURE get_food_by_supplier_id
(
    p_food_supplier_id IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT * FROM food_table 
    WHERE food_supplier_id = p_food_supplier_id;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error fetching food by supplier ID: ' || SQLERRM);
END;
