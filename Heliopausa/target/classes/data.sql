-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

-- insert into APP_USER(ID, PASSWORD, USERNAME) values(1, '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'svlada@gmail.com');
-- insert into USER_ROLE(APP_USER_ID, ROLE) values(1, 'ADMIN');
-- insert into USER_ROLE(APP_USER_ID, ROLE) values(1, 'PREMIUM_MEMBER');

insert into role(id, name, description) values(1, 'ROLE_USER', 'user of the system');
insert into role(id, name, description) values(2, 'ROLE_ADMIN', 'administrator of the system');
insert into role(id, name, description) values(3, 'ROLE_MANAGEMENT', 'management of the system');
insert into role(id, name, description) values(4, 'ROLE_GUEST', 'guest or invited of the system');

