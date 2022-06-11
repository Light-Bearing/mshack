INSERT INTO ROLES (name,name_eng,name_rus)
VALUES ('ROLE_USER','User','Пользователь'),
       ('ROLE_ADMIN','Administrator','Администратор'),

INSERT INTO USERS (username,email, surname, name, patronymic, PASSWORD)
VALUES ('user','user@admin.ru', 'User_Last', 'User_First','User_middle', '{noop}password'),
       ('admin','admin@admin.ru','Admin_Last', 'Admin_First', 'admin_middle', '{noop}admin'),
       ('testtest','test@test.test','test_Last', 'test_First', 'test_middle', '$2a$10$.cpMS3flrNkcSauSCludMe9rIqkonPemlwJ6pa3QcuJ/CC3iPlLra');

INSERT INTO USER_ROLES (USER_ID, ROLE_ID)
VALUES (1,1),
       (2,2),
       (3,2);


