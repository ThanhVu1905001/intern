CREATE schema ex3;
use ex3;
CREATE TABLE warehouse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    warehouse_code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE category (
	id INT AUTO_INCREMENT PRIMARY KEY,
    category_code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    category_description TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE product (
	id INT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(255) NOT NULL,
    category_code VARCHAR(255) NOT NULL,
    warehouse_code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    product_description TEXT,
    image_path VARCHAR(255),
    quantity_in_stock INT,
    quantity_sold INT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO warehouse (warehouse_code, name, location)
VALUES
    ('W001', 'Warehouse 1', 'New York'),
    ('W002', 'Warehouse 2', 'Los Angeles'),
    ('W003', 'Warehouse 3', 'Chicago'),
    ('W004', 'Warehouse 4', 'Houston'),
    ('W005', 'Warehouse 5', 'Miami'),
    ('W006', 'Warehouse 6', 'San Francisco'),
    ('W007', 'Warehouse 7', 'Seattle'),
    ('W008', 'Warehouse 8', 'Dallas'),
    ('W009', 'Warehouse 9', 'Denver'),
    ('W010', 'Warehouse 10', 'Boston');

 INSERT INTO category (category_code, name, category_description)
VALUES
    ('Apple', 'Apple', 'Description for Category 1'),
    ('C002', 'Category 2', 'Description for Category 2'),
    ('C003', 'Category 3', 'Description for Category 3'),
    ('C004', 'Category 4', 'Description for Category 4'),
    ('C005', 'Category 5', 'Description for Category 5'),
    ('C006', 'Category 6', 'Description for Category 6'),
    ('C007', 'Category 7', 'Description for Category 7'),
    ('C008', 'Category 8', 'Description for Category 8'),
    ('C009', 'Category 9', 'Description for Category 9'),
    ('C010', 'Category 10', 'Description for Category 10');

INSERT INTO product (product_code, category_code, warehouse_code, name, price, product_description, image_path, quantity_in_stock, quantity_sold)
VALUES
    ('P001', 'Apple', 'iphone', 'Product 1 Điện Thoại', '10.99', 'Description for Product 1', 'path_to_image1.jpg', 100, 0),
    ('P002', 'Apple', 'iphone', 'Product 2', '19.99', 'Description for Product 2', 'path_to_image2.jpg', 200, 20),
    ('P003', 'Apple', 'iphone', 'Product 3 Điện Thoại', '5.99', 'Description for Product 3', 'path_to_image3.jpg', 50, 10),
    ('P004', 'Galazy', 'samsung', 'Product 4', '12.49', 'Description for Product 4', 'path_to_image4.jpg', 150, 30),
    ('P005', 'Galazy', 'samsung', 'Product 5', '8.99', 'Description for Product 5', 'path_to_image5.jpg', 80, 5),
    ('P006', 'Galazy', 'samsung', 'Product 6', '15.99', 'Description for Product 6', 'path_to_image6.jpg', 30, 15),
    ('P007', 'Galazy', 'samsung', 'Product 7', '7.99', 'Description for Product 7', 'path_to_image7.jpg', 70, 10),
    ('P008', 'oppa', 'oppo', 'Product 8', '11.99', 'Description for Product 8', 'path_to_image8.jpg', 120, 25),
    ('P009', 'oppa', 'oppo', 'Product 9', '14.99', 'Description for Product 9', 'path_to_image9.jpg', 90, 10),
    ('P010', 'holo', 'hili', 'Product 10', '9.99', 'Description for Product 10', 'path_to_image10.jpg', 40, 5);

UPDATE warehouse
SET warehouse_code = 'W003', name = 'Kho 3', location = 'San Francisco'
WHERE id = 1;

DELETE FROM warehouse
WHERE id = 2;

UPDATE product
SET product_code = 'P003', name = 'Product 3 Updated', price = '14.99'
WHERE id = 1;

DELETE FROM product
WHERE id = 2;

UPDATE category
SET category_code = 'C003', name = 'Category 3 Updated', category_description = 'Updated description'
WHERE id = 1;

DELETE FROM category
WHERE id = 2;

SELECT *
FROM product
WHERE name LIKE '%Điện Thoại%' 
  AND category_code = 'Apple';

SELECT category_code, COUNT(*) AS number_product
FROM product
group by category_code
order by number_product DESC;

START TRANSACTION; -- Bắt đầu giao dịch

-- Xóa tất cả sản phẩm thuộc danh mục cần xóa
DELETE FROM product
WHERE category_code = 'C003';

-- Xóa danh mụcproductproduct
DELETE FROM category
WHERE category_code = 'C003';

COMMIT; -- Hoàn thành giao dịch

SELECT *
FROM product
ORDER BY quantity_sold DESC
LIMIT 10;

-- tạo index 
CREATE INDEX product_code_index ON product (product_code);

-- trả về index
SHOW INDEX FROM product;

-- xóa index
DROP INDEX product_code_index ON product;

DELIMITER //

CREATE PROCEDURE SearchProducts
(
    IN productCode VARCHAR(255),
    IN productName VARCHAR(255),
    IN warehouseCode VARCHAR(255),
    IN categoryCode VARCHAR(255),
    IN creationTime TIMESTAMP,
    IN page INT,
    IN pageSize INT
)
BEGIN
    DECLARE start INT;
    DECLARE end INT;

    -- Calculate the start and end positions for pagination
    SET start = (page - 1) * pageSize;
    SET end = page * pageSize;
    
	DROP TEMPORARY TABLE IF EXISTS TempResults;

    -- Create a temporary table to store the results
    CREATE TEMPORARY TABLE TempResults
    (
        productCode VARCHAR(255),
        productName VARCHAR(255),
        warehouseCode VARCHAR(255),
        categoryCode VARCHAR(255),
        creationTime TIMESTAMP
    );

    INSERT INTO TempResults
    SELECT p.product_code, p.name, p.warehouse_code, p.category_code, p.created_date
    FROM product AS p
    INNER JOIN warehouse AS w ON p.warehouse_code = w.warehouse_code
    INNER JOIN category AS c ON p.category_code = c.category_code
    WHERE (productCode IS NULL OR p.product_code = productCode)
        AND (productName IS NULL OR p.name = productName)
        AND (warehouseCode IS NULL OR p.warehouse_code = warehouseCode)
        AND (categoryCode IS NULL OR p.category_code = categoryCode)
        AND (creationTime IS NULL OR p.created_date = creationTime);

    -- Return the paginated results
    SELECT *
    FROM TempResults;

    -- Drop the temporary table
    DROP TEMPORARY TABLE TempResults;
END //
DELIMITER ;


DROP PROCEDURE SearchProducts;

-- Chạy stored procedure SearchProducts
CALL SearchProducts('P001', NULL, 'W001', 'C001', NULL, 1, 10);

DELIMITER //

CREATE PROCEDURE SearchProduct
(
    IN p_productCode VARCHAR(255),
    IN p_productName VARCHAR(255),
    IN p_warehouseCode VARCHAR(255),
    IN p_categoryCode VARCHAR(255),
    IN p_creationTime DATETIME,
    IN p_page INT
)
BEGIN
    DECLARE p_pageSize INT;
    DECLARE p_offset INT;
    
    SET p_pageSize = 10;
    SET p_offset = (p_page - 1) * p_pageSize;

    -- Create a temporary table to store the results
    CREATE TEMPORARY TABLE TempResults
    (
        product_code VARCHAR(255),
        name VARCHAR(255),
        warehouse_code VARCHAR(255),
        category_code VARCHAR(255),
        created_date DATETIME
    );

    -- Build the WHERE clause based on the provided parameters
    SET @whereClause = '';
    IF p_productCode IS NOT NULL THEN
        SET @whereClause = CONCAT(@whereClause, ' AND product_code = "', p_productCode, '"');
    END IF;
    IF p_productName IS NOT NULL THEN
        SET @whereClause = CONCAT(@whereClause, ' AND name = "', p_productName, '"');
    END IF;
    IF p_warehouseCode IS NOT NULL THEN
        SET @whereClause = CONCAT(@whereClause, ' AND warehouse_code = "', p_warehouseCode, '"');
    END IF;
    IF p_categoryCode IS NOT NULL THEN
        SET @whereClause = CONCAT(@whereClause, ' AND category_code = "', p_categoryCode, '"');
    END IF;
    IF p_creationTime IS NOT NULL THEN
        SET @whereClause = CONCAT(@whereClause, ' AND created_date = "', p_creationTime, '"');
    END IF;

    -- Remove the leading "AND" from the WHERE clause
    IF LENGTH(@whereClause) > 0 THEN
        SET @whereClause = SUBSTRING(@whereClause, 5);
    END IF;

    -- Build the final SQL query
    SET @sqlQuery = CONCAT(
        'INSERT INTO TempResults (product_code, name, warehouse_code, category_code, created_date) ',
        'SELECT product_code, name, warehouse_code, category_code, created_date FROM product WHERE ',
        @whereClause,
        ' LIMIT ', p_offset, ', ', p_pageSize
    );

    -- Execute the SQL query
    PREPARE finalQuery FROM @sqlQuery;
    EXECUTE finalQuery;
    DEALLOCATE PREPARE finalQuery;

    -- Return the results
    SELECT * FROM TempResults;

    -- Drop the temporary table
    DROP TEMPORARY TABLE TempResults;
END //

DELIMITER ;

DROP PROCEDURE SearchProduct;

-- Chạy stored procedure SearchProducts
CALL SearchProduct('P001', NULL, 'W001', 'C001', NULL, 2);

