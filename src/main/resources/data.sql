insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, description)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Admin'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Manager'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Employee');


insert into addresses(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                      last_update_user_id, address_line1, address_line2, city, state, country, zip_code)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, '123 Main Street', 'apt 4B San Diego CA, 91911', 'Los Angeles', 'California', 'United States Of America', '91911'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'blue street', 'blue avenue 24', 'Tbilisi', 'Tbilisi', 'Georgia', '0164'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'green street', 'green avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0165'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'purple street', 'purple avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0166'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'black street', 'black avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0167'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'yellow street', 'yellow avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0168'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'pink street', 'pink avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0169');



INSERT INTO companies(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                      last_update_user_id, title, phone, website, email, address_id)
VALUES
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Red Tech', '+(12)-345-678', 'www.redtech.com', 'redtech@email.com', 1),
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Blue Tech', '+(12)-456-789', 'www.bluetech.com', 'bluetech@email.com', 2),
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Green Solutions', '+(12)-678-123', 'www.greensolutions.com', 'greensolutions@email.com', 3),
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Yellow Innovations', '+(12)-234-567', 'www.yellowinnovations.com', 'yellow@email.com', 4),
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Purple Corp', '+(12)-789-345', 'www.purplecorp.com', 'purple@email.com', 5),
    ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Orange Enterprises', '+(12)-123-456', 'www.orangeenterprises.com', 'orange@email.com', 6);


INSERT INTO users(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                  first_name, last_name, username, password, enabled, phone, date_of_employment, role_id, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Harold', 'Finch', 'haroldfinch@email.com', 'Abc1', true, '0123456789',  '2022-01-05' ,1, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'John', 'Reese', 'johnreese@email.com', 'Abc1', true, '9876543210', '2022-01-05' , 1, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Lionel', 'Fusco', 'lionelfusco@email.com', 'Abc1', true, '0987612345',  '2022-01-05' ,3, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Sameen', 'Shaw', 'sameenshaw@email.com', 'Abc1', true, '1234509876', '2022-01-05' , 2, 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samantha', 'Groves', 'samanthagroves@email.com', 'Abc1', true, '0192837465',  '2022-01-05' ,1, 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Joselyn', 'Carter', 'joselyncarter@email.com', 'Abc1', true, '0198237645', '2022-01-05' , 3, 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Carl', 'Elias', 'carlelias@email.com', 'Abc1', true, '5610298473', '2022-01-05', 3, 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Patrick', 'Jane', 'patrickjane@email.com', 'Abc1', true, '6758492013', '2022-01-05' , 1, 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Wayne', 'Rigsby', 'waynerigsby@email.com', 'Abc1', true, '0934871256', '2022-01-05' , 3, 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kimball', 'Cho', 'kimballcho@email.com', 'Abc1', true, '9078653124', '2022-01-05' , 2, 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Teresa', 'Lisbon', 'teresalisbon@email.com', 'Abc1', true, '5674019283', '2022-01-05' , 1, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Grace', 'Van Pelt', 'gracevanpelt@email.com', 'Abc1', true, '1386709254', '2022-01-05' , 2, 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Dennis', 'Abbott', 'dennisabbott@email.com', 'Abc1', true, '0642871359', '2022-01-05' , 1, 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Jason', 'Wylie', 'jasonwylie@email.com', 'Abc1', true, '9081263547', '2022-01-05' , 2, 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Michelle', 'Vega', 'michellevega@email.com', 'Abc1', true, '0912537648', '2022-01-05' , 3, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kim', 'Fischer', 'kimfischer@email.com', 'Abc1', true, '1209786345', '2022-01-05' , 2, 1);


INSERT INTO clients_vendors(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                            name, phone, website, email, client_vendor_type, address_id, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samsung', '0123456789', 'samsung.com', 'l.varsimashvil10@gmail.com', 'CLIENT', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Apple', '0123456789', 'apple.com', 'apple@email.com', 'VENDOR', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Google', '0123456789', 'google.com', 'google@email.com', 'CLIENT', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Amazon', '0123456789', 'amazon.com', 'amazon@email.com', 'VENDOR', 4, 1);

-- Insert categories
INSERT INTO categories(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                       description, icon, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Groceries', 'Leaf', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Electronics', 'Laptop', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Clothing', 'ShoppingBag', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Books', 'Book', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Beauty & Personal Care', 'Leaf', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Furniture', 'Home', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Sports & Outdoors', 'Heart', 1);

-- Insert products
INSERT INTO products(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     name, description, quantity_in_stock, low_limit_alert, price, created_at, product_unit, status, category_id)
VALUES
-- Groceries
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Milk', '1 liter', 50, 10, 1.5, '2024-01-05', 'LITER', 'ACTIVE', 1),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Eggs', 'Pack of 12', 30, 5, 2.0, '2024-01-05', 'PACK', 'ACTIVE', 1),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Bread', 'Whole wheat', 40, 5, 2.5, '2024-01-05', 'LOAF', 'ACTIVE', 1),

-- Electronics
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Smartphone', '5G Enabled', 20, 3, 699.99, '2024-01-05', 'UNIT', 'ACTIVE', 2),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Laptop', '16GB RAM, 512GB SSD', 10, 2, 999.99, '2024-01-05', 'UNIT', 'ACTIVE', 2),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Headphones', 'Wireless', 25, 5, 59.99, '2024-01-05', 'UNIT', 'ACTIVE', 2),

-- Clothing
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'T-Shirt', 'Cotton, Medium', 100, 10, 15.99, '2024-01-05', 'UNIT', 'ACTIVE', 3),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Jeans', 'Blue, Size 32', 50, 5, 39.99, '2024-01-05', 'UNIT', 'ACTIVE', 3),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Sneakers', 'Running Shoes', 30, 5, 49.99, '2024-01-05', 'UNIT', 'ACTIVE', 3),

-- Books
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'The Great Gatsby', 'Classic Literature', 15, 3, 10.99, '2024-01-05', 'UNIT', 'ACTIVE', 4),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Java Programming', 'Tech Book', 10, 2, 49.99, '2024-01-05', 'UNIT', 'ACTIVE', 4),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Cookbook', 'Healthy Recipes', 20, 5, 25.99, '2024-01-05', 'UNIT', 'ACTIVE', 4),

-- Beauty & Personal Care
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Shampoo', '500ml Bottle', 40, 5, 8.99, '2024-01-05', 'UNIT', 'ACTIVE', 5),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Lipstick', 'Red', 30, 3, 12.99, '2024-01-05', 'UNIT', 'ACTIVE', 5),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Face Wash', 'Gentle Cleanser', 50, 10, 6.99, '2024-01-05', 'UNIT', 'ACTIVE', 5),

-- Furniture
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Dining Table', 'Wooden, 6-seater', 5, 1, 299.99, '2024-01-05', 'UNIT', 'ACTIVE', 6),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Chair', 'Ergonomic Office Chair', 20, 5, 89.99, '2024-01-05', 'UNIT', 'ACTIVE', 6),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Sofa', '3-seater, Grey', 3, 1, 499.99, '2024-01-05', 'UNIT', 'ACTIVE', 6),

-- Sports & Outdoors
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Football', 'Standard Size', 15, 3, 19.99, '2024-01-05', 'UNIT', 'ACTIVE', 7),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Yoga Mat', 'Non-slip', 25, 5, 29.99, '2024-01-05', 'UNIT', 'ACTIVE', 7),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Water Bottle', 'Stainless Steel', 10, 3, 13.99 , '2024-01-05', 'UNIT', 'ACTIVE', 7);


INSERT INTO invoices(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     invoice_no, invoice_type, invoice_status, date_of_issue, due_date, payment_terms, notes,
                     client_vendor_id, company_id, accept_date)
VALUES  ('2024-11-05 00:00:00', 1, '2024-11-05 00:00:00', 1, false, 'INV001', 'SALES', 'AWAITING_APPROVAL', '2024-11-10 00:00:00', '2024-11-30 00:00:00', 'Net 20', 'Urgent delivery required', 1, 1, null),
        ('2024-11-06 00:00:00', 1, '2024-11-06 00:00:00', 1, false, 'INV002', 'SALES', 'APPROVED', '2024-11-11 00:00:00', '2024-11-30 00:00:00', 'Net 30', 'Standard terms', 2, 1, '2024-11-13 00:00:00'),
        ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV003', 'SALES', 'APPROVED', '2024-11-09 00:00:00', '2024-11-20 00:00:00', 'Net 10', 'Early payment discount', 1, 1, '2024-11-14 00:00:00'),
        ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV004', 'SALES', 'APPROVED', '2024-11-08 00:00:00', '2024-11-25 00:00:00', 'Net 15', 'Bulk order', 2, 4, '2024-11-15 00:00:00'),
        ('2024-11-09 00:00:00', 1, '2024-11-09 00:00:00', 1, false, 'INV005', 'SALES', 'AWAITING_APPROVAL', '2024-11-07 00:00:00', '2024-11-28 00:00:00', 'Net 20', 'Special discount applied', 1, 1, null);


INSERT INTO invoice_products(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                             price, quantity, remaining_quantity, tax, total, profit_loss, invoice_id, product_id)
VALUES
-- Invoice 1
('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 100, 5, 0, 5, 525, 25, 1, 1),
('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 200, 2, 0, 10, 420, 20, 1, 2),

-- Invoice 2
('2024-11-11 00:00:00', 2, false, '2024-11-11 00:00:00', 2, 150, 10, 5, 8, 1650, 150, 2, 3),
('2024-11-11 00:00:00', 2, false, '2024-11-11 00:00:00', 2, 250, 4, 1, 12, 1120, 100, 2, 4),

-- Invoice 3
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 8, 3, 10, 660, 50, 3, 5),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 6, 2, 8, 550, 40, 3, 6),

-- Invoice 4
('2024-11-13 00:00:00', 2, false, '2024-11-13 00:00:00', 2, 120, 7, 0, 5, 890, 70, 4, 7),
('2024-11-13 00:00:00', 2, false, '2024-11-13 00:00:00', 2, 130, 3, 1, 6, 410, 30, 4, 8),

-- Invoice 5
('2024-11-14 00:00:00', 2, false, '2024-11-14 00:00:00', 2, 200, 6, 4, 12, 1560, 120, 5, 9),
('2024-11-14 00:00:00', 2, false, '2024-11-14 00:00:00', 2, 300, 3, 0, 15, 1035, 90, 5, 10);

