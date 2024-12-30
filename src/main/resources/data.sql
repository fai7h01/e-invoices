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
                  first_name, last_name, username, password, enabled, phone, date_of_employment, role_id, company_id, user_status)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'John', 'Reese', 'johnreese@email.com', 'Abc1', true, '9876543210', '2022-01-05' , 1, 1, 'Active'),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Lionel', 'Fusco', 'lionelfusco@email.com', 'Abc1', true, '0987612345',  '2022-01-05' ,3, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Teresa', 'Lisbon', 'teresalisbon@email.com', 'Abc1', true, '1234509876', '2022-01-05' , 2, 1, 'Active');
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samantha', 'Groves', 'samanthagroves@email.com', 'Abc1', true, '0192837465',  '2022-01-05' ,1, 2),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Joselyn', 'Carter', 'joselyncarter@email.com', 'Abc1', true, '0198237645', '2022-01-05' , 3, 2),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Carl', 'Elias', 'carlelias@email.com', 'Abc1', true, '5610298473', '2022-01-05', 3, 2),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Patrick', 'Jane', 'patrickjane@email.com', 'Abc1', true, '6758492013', '2022-01-05' , 1, 3),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Wayne', 'Rigsby', 'waynerigsby@email.com', 'Abc1', true, '0934871256', '2022-01-05' , 3, 3),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kimball', 'Cho', 'kimballcho@email.com', 'Abc1', true, '9078653124', '2022-01-05' , 2, 3),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Teresa', 'Lisbon', 'teresalisbon@email.com', 'Abc1', true, '5674019283', '2022-01-05' , 1, 1),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Grace', 'Van Pelt', 'gracevanpelt@email.com', 'Abc1', true, '1386709254', '2022-01-05' , 2, 3),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Dennis', 'Abbott', 'dennisabbott@email.com', 'Abc1', true, '0642871359', '2022-01-05' , 1, 2),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Jason', 'Wylie', 'jasonwylie@email.com', 'Abc1', true, '9081263547', '2022-01-05' , 2, 3),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Michelle', 'Vega', 'michellevega@email.com', 'Abc1', true, '0912537648', '2022-01-05' , 3, 1),
--        ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kim', 'Fischer', 'kimfischer@email.com', 'Abc1', true, '1209786345', '2022-01-05' , 2, 1);


INSERT INTO clients_vendors(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                            name, phone, website, email, client_vendor_type, address_id, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samsung', '0123456789', 'samsung.com', 'l.varsimashvil10@gmail.com', 'Client', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Apple', '0123456789', 'apple.com', 'apple@email.com', 'Vendor', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Google', '0123456789', 'google.com', 'google@email.com', 'Client', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Amazon', '0123456789', 'amazon.com', 'amazon@email.com', 'Vendor', 4, 1);

-- Insert categories
INSERT INTO categories(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                       description, icon, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Groceries', 'Leaf', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Electronics', 'Laptop', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Clothing', 'ShoppingBag', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Book', 'Book', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Beauty & Personal Care', 'Leaf', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Furniture', 'Home', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Sports & Outdoors', 'Heart', 1);

-- Insert products
INSERT INTO products(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     name, description, quantity_in_stock, low_limit_alert, price, created_at, product_unit, status, category_id, currency)
VALUES
-- Groceries
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Milk', '1 liter', 100, 10, 5, '2024-10-05', 'L', 'ACTIVE', 1, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Eggs', 'Pack of 12', 100, 5, 3, '2024-10-05', 'PACK', 'ACTIVE', 1, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Bread', 'Whole wheat', 100, 5, 4, '2024-10-05', 'PACK', 'ACTIVE', 1, 'USD'), --12usd

-- Electronics
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Smartphone', '5G Enabled', 100, 3, 700, '2024-11-05', 'UNIT', 'ACTIVE', 2, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Laptop', '16GB RAM, 512GB SSD', 100, 2, 1000, '2024-11-05', 'UNIT', 'ACTIVE', 2, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Headphones', 'Wireless', 100, 5, 60, '2024-11-05', 'UNIT', 'ACTIVE', 2, 'USD'), --1758.97usd

-- Clothing
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'T-Shirt', 'Cotton, Medium', 100, 10, 15, '2024-12-05', 'UNIT', 'ACTIVE', 3, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Jeans', 'Blue, Size 32', 100, 5, 40, '2024-12-05', 'UNIT', 'ACTIVE', 3, 'USD'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Sneakers', 'Running Shoes', 100, 5, 50, '2024-12-05', 'UNIT', 'ACTIVE', 3, 'USD'), --95.97

-- Book
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'The Great Gatsby', 'Classic Literature', 100, 3, 10, '2024-11-05', 'UNIT', 'ACTIVE', 4, 'GEL'), --1000gel
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Java Programming', 'Tech Book', 100, 2, 50, '2024-11-05', 'UNIT', 'ACTIVE', 4, 'GEL'), --5000
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Cookbook', 'Healthy Recipes', 100, 5, 25, '2024-11-05', 'UNIT', 'ACTIVE', 4, 'GEL'), -- 2500

-- Beauty & Personal Care
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Shampoo', '500ml Bottle', 100, 5, 8.99, '2024-01-05', 'UNIT', 'ACTIVE', 5, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Lipstick', 'Red', 100, 3, 12.99, '2024-01-05', 'UNIT', 'ACTIVE', 5, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Face Wash', 'Gentle Cleanser', 100, 10, 6.99, '2024-01-05', 'UNIT', 'ACTIVE', 5, 'GEL'),

-- Furniture
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Dining Table', 'Wooden, 6-seater', 100, 1, 299.99, '2024-01-05', 'UNIT', 'ACTIVE', 6, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Chair', 'Ergonomic Office Chair', 100, 5, 89.99, '2024-01-05', 'UNIT', 'ACTIVE', 6, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Sofa', '3-seater, Grey', 100, 1, 499.99, '2024-01-05', 'UNIT', 'ACTIVE', 6, 'GEL'),

-- Sports & Outdoors
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Football', 'Standard Size', 100, 3, 19.99, '2024-01-05', 'UNIT', 'ACTIVE', 7, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Yoga Mat', 'Non-slip', 100, 5, 29.99, '2024-01-05', 'UNIT', 'ACTIVE', 7, 'GEL'),
('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Water Bottle', 'Stainless Steel', 100, 3, 13.99 , '2024-01-05', 'UNIT', 'ACTIVE', 7, 'GEL');


INSERT INTO invoices(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     invoice_no, invoice_status, date_of_issue, due_date, payment_terms, notes,
                     client_vendor_id, company_id, accept_date, currency)
VALUES  ('2024-11-05 00:00:00', 1, '2024-11-05 00:00:00', 1, false, 'INV001', 'APPROVED', '2024-11-10 00:00:00', '2024-11-30 00:00:00', 'Net 20', 'Urgent delivery required', 1, 1, null, 'USD'),
        ('2024-11-06 00:00:00', 1, '2024-11-06 00:00:00', 1, false, 'INV002', 'AWAITING_APPROVAL', '2024-11-11 00:00:00', '2024-11-30 00:00:00', 'Net 30', 'Standard terms', 2, 1, '2024-01-13 00:00:00', 'USD'),
        ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV003', 'APPROVED', '2024-11-09 00:00:00', '2024-11-20 00:00:00', 'Net 10', 'Net 20', 3, 1, '2024-01-14 00:00:00', 'GEL'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV004', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 4, 1, '2024-02-01 00:00:00', 'GEL'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV005', 'AWAITING_APPROVAL', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 1, 1, '2024-02-02 00:00:00', 'USD'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV006', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 2, 1, '2024-03-03 00:00:00', 'USD'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV007', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 3, 1, '2024-03-04 00:00:00', 'USD'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV008', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 4, 1, '2024-04-05 00:00:00', 'GEL'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV009', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 1, 1, '2024-04-06 00:00:00', 'GEL'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV010', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Net 20', 2, 1, '2024-05-07 00:00:00', 'USD'),
         ('2024-11-07 00:00:00', 1, '2024-11-07 00:00:00', 1, false, 'INV011', 'APPROVED', '2024-11-09 00:00:00', '2024-12-20 00:00:00', 'Net 10', 'Early payment discount', 3, 1, '2024-06-08 00:00:00', 'GEL'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV012', 'APPROVED', '2024-11-10 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1, '2024-07-09 00:00:00', 'EUR'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV013', 'APPROVED', '2024-11-11 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1, '2024-08-10 00:00:00', 'USD'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV014', 'APPROVED', '2024-11-12 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1, '2024-08-11 00:00:00', 'EUR'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV015', 'APPROVED', '2024-11-12 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1, '2024-09-12 00:00:00', 'USD'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV016', 'APPROVED', '2024-11-12 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1, '2024-10-13 00:00:00', 'EUR'),
         ('2024-11-08 00:00:00', 1, '2024-11-08 00:00:00', 1, false, 'INV017', 'APPROVED', '2024-11-12 00:00:00', '2024-12-25 00:00:00', 'Net 15', 'Bulk order', 2, 1,  '2024-12-14 00:00:00', 'GEL');


INSERT INTO invoice_products(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                             price, quantity, remaining_quantity, tax, total, profit_loss, invoice_id, product_id, currency)
VALUES
-- Invoice 1
('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 10, 20, 80, 0, 525, 25,1, 1, 'USD'),  --200 USD ; q = 20
('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 7, 30, 70, 0, 420, 20, 1, 1, 'USD'),  --210 USD ; q = 30
('2024-11-11 00:00:00', 2, false, '2024-11-11 00:00:00', 2, 6, 25, 75, 0, 1650, 150, 1, 3, 'USD'),--150 USD ; q = 25 sum = 75

('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 10, 20, 80, 0, 525, 25, 2, 2, 'USD'),
('2024-11-10 00:00:00', 2, false, '2024-11-10 00:00:00', 2, 7, 30, 70, 0, 420, 20, 2, 2, 'USD'),
('2024-11-11 00:00:00', 2, false, '2024-11-11 00:00:00', 2, 6, 25, 75, 0, 1650, 150, 2, 4, 'USD'),

('2024-11-11 00:00:00', 2, false, '2024-11-11 00:00:00', 2, 5, 5, 95, 0, 1120, 100, 3, 1, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 100, 45, 0, 660, 50, 3, 5, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 100, 90, 0, 550, 40, 3, 6, 'GEL'),

('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 1, 10, 45, 0, 660, 50, 4, 7, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 2, 10, 45, 0, 660, 50, 4, 8, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 35, 10, 45, 0, 660, 50, 4, 9, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 40, 10, 45, 0, 660, 50, 5, 10, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 5, 10, 45, 0, 660, 50, 5, 11, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 4, 10, 45, 0, 660, 50, 6, 12, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 10, 10, 45, 0, 660, 50, 6, 13, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 51, 10, 45, 0, 660, 50, 6, 14, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 60, 10, 45, 0, 660, 50, 7, 15, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 8.5, 10, 45, 0, 660, 50, 7, 16, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 20, 10, 45, 0, 660, 50, 8, 17, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 30, 10, 45, 0, 660, 50, 8, 19, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 15, 10, 45, 0, 660, 50, 8, 18, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 40, 10, 45, 0, 660, 50, 9, 20, 'GEL'),


-- -- Invoice 4
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 20, 3, 10, 660, 50, 10, 1, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 10, 2, 8, 550, 40, 10, 2, 'USD'),

-- Invoice 5
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 5, 3, 10, 660, 50, 11, 3, 'GEL'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 10, 2, 8, 550, 40, 11, 4, 'GEL'),

-- Invoice 6
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 35, 3, 10, 660, 50, 12, 5, 'EUR'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 5, 2, 8, 550, 40, 12, 6, 'EUR'),

-- Invoice 7
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 15, 3, 10, 660, 50, 12, 2, 'EUR'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 15, 2, 8, 550, 40, 12, 2, 'EUR'),

-- Invoice 8
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 20, 3, 10, 660, 50, 13, 1, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 15, 2, 8, 550, 40, 13, 5, 'USD'),

-- Invoice 9
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 10, 3, 10, 660, 50, 14, 2, 'EUR'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 20, 2, 8, 550, 40, 14, 3, 'EUR'),

-- Invoice 10
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 75, 25, 3, 10, 660, 50, 15, 1, 'USD'),
('2024-11-12 00:00:00', 2, false, '2024-11-12 00:00:00', 2, 90, 15, 2, 8, 550, 40, 15, 5, 'USD'),

-- Invoice 4
('2024-11-13 00:00:00', 2, false, '2024-11-13 00:00:00', 2, 120, 10, 0, 5, 890, 70, 16, 7, 'EUR'),
('2024-11-13 00:00:00', 2, false, '2024-11-13 00:00:00', 2, 130, 5, 1, 6, 410, 30, 16, 8, 'EUR'),

-- Invoice 5
('2024-11-14 00:00:00', 2, false, '2024-11-14 00:00:00', 2, 200, 5, 4, 12, 1560, 120, 17, 9, 'GEL'),
('2024-11-14 00:00:00', 2, false, '2024-11-14 00:00:00', 2, 300, 5, 0, 15, 1035, 90, 17, 10, 'GEL');
