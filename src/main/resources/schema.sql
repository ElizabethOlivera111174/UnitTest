create sequence hibernate_sequence start with 1 increment by 1;
create table country (country_id bigint not null, iso_code varchar(255), country_name varchar(255), country_capital varchar(255), country_independence_date varchar(255), primary key (country_id));
create table user (id bigint not null, name varchar(255), last_name varchar(255), email varchar(255), password varchar(255),primary key (id));

