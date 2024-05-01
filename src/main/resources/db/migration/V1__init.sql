-- auto-generated definition
create table category
(
    id         int          not null
        primary key,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    name       varchar(255) null
);

-- auto-generated definition
create table product
(
    category_id int          null,
    id          int          not null
        primary key,
    price       double       not null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    description varchar(255) null,
    imageurl    varchar(255) null,
    title       varchar(255) null,
    constraint product_category_fk
        foreign key (category_id) references category (id)
);

create table category_seq (next_val bigint) engine=InnoDB;
insert into category_seq values ( 1 );

create table product_seq (next_val bigint) engine=InnoDB;
insert into product_seq values ( 1 );