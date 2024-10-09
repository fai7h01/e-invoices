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


INSERT INTO users(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted,
                  first_name, last_name, username, password, enabled, phone, gender, role_id)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Harold', 'Finch', 'haroldfinch@email.com', 'Abc1', true, '0123456789', 'MALE', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'John', 'Reese', 'johnreese@email.com', 'Abc1', true, '9876543210', 'MALE', 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Lionel', 'Fusco', 'lionelfusco@email.com', 'Abc1', true, '0987612345', 'MALE', 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Sameen', 'Shaw', 'sameenshaw@email.com', 'Abc1', true, '1234509876', 'FEMALE', 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Samantha', 'Groves', 'samanthagroves@email.com', 'Abc1', true, '0192837465', 'FEMALE', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Joselyn', 'Carter', 'joselyncarter@email.com', 'Abc1', true, '0198237645', 'FEMALE', 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Carl', 'Elias', 'carlelias@email.com', 'Abc1', true, '5610298473', 'MALE', 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Patrick', 'Jane', 'patrickjane@email.com', 'Abc1', true, '6758492013', 'MALE', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Wayne', 'Rigsby', 'waynerigsby@email.com', 'Abc1', true, '0934871256', 'MALE', 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kimball', 'Cho', 'kimballcho@email.com', 'Abc1', true, '9078653124', 'MALE', 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Teresa', 'Lisbon', 'teresalisbon@email.com', 'Abc1', true, '5674019283', 'FEMALE', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Grace', 'Van Pelt', 'gracevanpelt@email.com', 'Abc1', true, '1386709254', 'FEMALE', 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Dennis', 'Abbott', 'dennisabbott@email.com', 'Abc1', true, '0642871359', 'MALE', 1),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Jason', 'Wylie', 'jasonwylie@email.com', 'Abc1', true, '9081263547', 'MALE', 2),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Michelle', 'Vega', 'michellevega@email.com', 'Abc1', true, '0912537648', 'FEMALE', 3),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, false, 'Kim', 'Fischer', 'kimfischer@email.com', 'Abc1', true, '1209786345', 'FEMALE', 2);

