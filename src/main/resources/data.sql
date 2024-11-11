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


insert into companies(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                      last_update_user_id, title, phone, website, email, address_id)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Red Tech', '+(12)-345-678', 'www.redtech.com','redtech@email.com', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'blue tech', '+(12)-345-678', 'www.bluetech.com','bluetech@email.com', 2),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'green tech', '345', 'www.greentech.com' ,'greentech@email.com', 3);


INSERT INTO users(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                  first_name, last_name, username, password, enabled, phone, gender, date_of_employment, role_id, company_id, ingested)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Harold', 'Finch', 'haroldfinch@email.com', 'Abc1', true, '0123456789', 'MALE', '2022-01-05' ,1, 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'John', 'Reese', 'johnreese@email.com', 'Abc1', true, '9876543210', 'MALE','2022-01-05' , 1, 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Lionel', 'Fusco', 'lionelfusco@email.com', 'Abc1', true, '0987612345', 'MALE', '2022-01-05' ,3, 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Sameen', 'Shaw', 'sameenshaw@email.com', 'Abc1', true, '1234509876', 'FEMALE','2022-01-05' , 2, 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samantha', 'Groves', 'samanthagroves@email.com', 'Abc1', true, '0192837465', 'FEMALE', '2022-01-05' ,1, 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Joselyn', 'Carter', 'joselyncarter@email.com', 'Abc1', true, '0198237645', 'FEMALE','2022-01-05' , 3, 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Carl', 'Elias', 'carlelias@email.com', 'Abc1', true, '5610298473', 'MALE','2022-01-05' , 3, 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Patrick', 'Jane', 'patrickjane@email.com', 'Abc1', true, '6758492013', 'MALE','2022-01-05' , 1, 3, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Wayne', 'Rigsby', 'waynerigsby@email.com', 'Abc1', true, '0934871256', 'MALE','2022-01-05' , 3, 3, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kimball', 'Cho', 'kimballcho@email.com', 'Abc1', true, '9078653124', 'MALE','2022-01-05' , 2, 3, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Teresa', 'Lisbon', 'teresalisbon@email.com', 'Abc1', true, '5674019283', 'FEMALE','2022-01-05' , 1, 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Grace', 'Van Pelt', 'gracevanpelt@email.com', 'Abc1', true, '1386709254', 'FEMALE','2022-01-05' , 2, 3, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Dennis', 'Abbott', 'dennisabbott@email.com', 'Abc1', true, '0642871359', 'MALE','2022-01-05' , 1, 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Jason', 'Wylie', 'jasonwylie@email.com', 'Abc1', true, '9081263547', 'MALE','2022-01-05' , 2, 3, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Michelle', 'Vega', 'michellevega@email.com', 'Abc1', true, '0912537648', 'FEMALE','2022-01-05' , 3, 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kim', 'Fischer', 'kimfischer@email.com', 'Abc1', true, '1209786345', 'FEMALE','2022-01-05' , 2, 1, false);


INSERT INTO clients_vendors(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                            name, phone, website, email, client_vendor_type, address_id, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samsung', '0123456789', 'samsung.com', 'samsung@email.com', 'CLIENT', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Apple', '0123456789', 'apple.com', 'apple@email.com', 'VENDOR', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Google', '0123456789', 'google.com', 'google@email.com', 'CLIENT', 4, 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Amazon', '0123456789', 'amazon.com', 'amazon@email.com', 'VENDOR', 4, 1);

INSERT INTO categories(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                       description, icon, company_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Accessories', 'ShoppingBag', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Cosmetics', 'Airplay', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Beverages', 'Aperture', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Books', 'Book' ,1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Dairy', 'Cake' ,1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Electronics', 'Home' ,1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Services', 'Box' ,1);

INSERT INTO products(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     name, description, quantity_in_stock, low_limit_alert, price, created_at, product_unit, status, category_id, ingested)
VALUES ('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Apple', 'Red',20, 5, 10, '2024-01-05', 'PCS', 'ACTIVE', 1, false),
       ('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Peach','Orange', 20, 5, 7 , '2024-01-05', 'PCS', 'DRAFT', 1, false),
       ('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Chocolate', 'Brown' , 20, 5, 8, '2024-01-05', 'PCS', 'ACTIVE', 1, false),
       ('2022-01-05 00:00:00',  1, '2022-01-05 00:00:00', 1, false, 'Ice-cream', 'White' ,20, 5, 9, '2024-01-05', 'PCS', 'DRAFT', 1, false);

INSERT INTO invoices(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                     invoice_no, invoice_type, invoice_status, date_of_issue, due_date,payment_terms, notes, client_vendor_id, company_id, ingested)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'INV001', 'SALES', 'APPROVED', '2024-10-23 00:00:00', '2024-11-23 00:00:00', 'terms', 'notes', 1 , 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'INV002', 'PURCHASE', 'APPROVED', '2024-10-23 00:00:00', '2024-07-23 00:00:00','terms', 'notes', 1 , 1, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'INV003', 'SALES', 'APPROVED', '2024-10-23 00:00:00', '2024-09-12 00:00:00','terms', 'notes', 1 , 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'INV004', 'PURCHASE', 'APPROVED', '2024-10-23 00:00:00', '2024-11-14 00:00:00','terms', 'notes', 1 , 2, false),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'INV005', 'SALES', 'APPROVED', '2024-10-23 00:00:00', '2024-12-23 00:00:00','terms', 'notes', 1 , 2, false);

INSERT INTO invoice_products(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                             price, quantity, remaining_quantity, tax, total, profit_loss, invoice_id, product_id, ingested)
VALUES
('2024-04-15 00:00', 2, 'false', '2024-04-15 00:00', 2, 20, 2, 20, 10, 0, 0, 1, 1, false),
('2024-04-15 00:00', 2, 'false', '2024-04-15 00:00', 2, 25, 5, 0, 10, 0, 0, 1, 2, false),
('2024-04-15 00:00', 2, 'false', '2024-04-15 00:00', 2, 300, 2, 0, 10, 0, 0, 1, 3, false),
('2024-04-15 00:00', 2, 'false', '2024-04-15 00:00', 2, 200, 2, 0, 10, 0, 0, 2, 3, false),
('2024-04-15 00:00', 2, 'false', '2024-04-15 00:00', 2, 300, 5, 0, 10, 0, 0, 2, 3, false);
