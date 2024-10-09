insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, description)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Admin'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Manager'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'Employee');


insert into addresses(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                      last_update_user_id, address_line1, address_line2, city, state, country, zip_code)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'red street', 'red avenue 23', 'Tbilisi', 'Tbilisi', 'Georgia', '0163'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'blue street', 'blue avenue 24', 'Tbilisi', 'Tbilisi', 'Georgia', '0164'),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'green street', 'green avenue 25', 'Tbilisi', 'Tbilisi', 'Georgia', '0165');


insert into companies(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                      last_update_user_id, title, phone, website, address_id)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'red tech', '345', 'redtech@email.com', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'blue tech', '345', 'bluetech@email.com', 2),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'green tech', '345', 'greentech@email.com', 3);


insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, username, password, first_name, last_name, phone, role_id, status, company_id)
values ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'john@email.com', 'Abc1', 'John', 'Doe', '123', 1, 'ACTIVE', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'melissa@email.com', 'Abc1', 'Melissa', 'Smith', '123', 2, 'ACTIVE', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'ruby@email.com', 'Abc1', 'Ruby', 'Green', '123', 3, 'ACTIVE', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'tom@email.com', 'Abc1', 'Tom', 'Hanks', '123', 3, 'ACTIVE', 1),
       ('2024-04-09 00:00:00', 1, false, '2024-04-09 00:00:00', 1, 'steve@email.com', 'Abc1', 'Steve', 'Jobs', '123', 3, 'ACTIVE', 2);


