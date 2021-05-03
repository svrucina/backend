insert into posts (id, user_id, comment, date, updated) values(1, 1, 'Prvi post', '2021-04-27 15:03:24.22', '2021-04-27 15:06:20.22');
insert into posts (id, user_id, comment, date, updated) values(2, 1, 'Drugi post', '2021-04-28 15:50:51.52', null);
insert into posts (id, user_id, comment, date, updated) values(3, 2, 'Treci post', '2021-04-29 12:09:29.11', null);
insert into posts (id, user_id, comment, date, updated) values(4, 3, 'Cetvrti post', '2021-04-29 13:03:44.32', '2021-04-29 13:09:33.32');


insert into user (id, username, password, first_name, last_name)
values (1, 'user', '$2y$12$nvSjn2VQfXoKTsM9auhI9uE2scFTNLLMqqKw.qTQCSaEl4lZdKBd2', 'Obican', 'User');
insert into user (id, username, password, first_name, last_name)
values (2, 'admin', '$2y$12$nvSjn2VQfXoKTsM9auhI9uE2scFTNLLMqqKw.qTQCSaEl4lZdKBd2', 'Neki', 'Admin');
insert into user (id, username, password, first_name, last_name)
values (3, 'stipe', '$2y$12$nvSjn2VQfXoKTsM9auhI9uE2scFTNLLMqqKw.qTQCSaEl4lZdKBd2', 'Stjepan', 'Vrucina');
insert into user (id, username, password, first_name, last_name)
values (4, 'pero', '$2y$12$nvSjn2VQfXoKTsM9auhI9uE2scFTNLLMqqKw.qTQCSaEl4lZdKBd2', 'Pero', 'Peric');


insert into authority (id, name)
values (1, 'ROLE_ADMIN');
insert into authority (id, name)
values (2, 'ROLE_USER');


insert into user_authority (user_id, authority_id)
values (1, 2);
insert into user_authority (user_id, authority_id)
values (2, 1);
insert into user_authority (user_id, authority_id)
values (3, 2);
insert into user_authority (user_id, authority_id)
values (4, 2);
