
    create table my_tasks (
       id binary(16) not null,
        content varchar(255),
        date date,
        time time,
        primary key (id)
    ) engine=InnoDB;

    create table users (
       id binary(16) not null,
        email varchar(255),
        password varchar(255),
        user_name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table my_tasks (
       id binary(16) not null,
        content varchar(255),
        date date,
        time time,
        primary key (id)
    ) engine=InnoDB;

    create table users (
       id binary(16) not null,
        email varchar(255),
        password varchar(255),
        user_name varchar(255),
        primary key (id)
    ) engine=InnoDB;
