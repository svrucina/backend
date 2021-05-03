create table if not exists posts(
    id identity,
    user_id bigint,
    comment varchar(300),
    date timestamp,
    updated timestamp
);
create table if not exists user (
                                    id identity,
                                    username varchar(100) not null,
                                    password varchar(250) not null,
                                    first_name varchar(24) not null,
                                    last_name varchar(24) not null
);
create table if not exists authority (
                                         id identity,
                                         name varchar(100) not null
);
create table if not exists user_authority (
                                              user_id bigint not null,
                                              authority_id bigint not null,
                                              constraint fk_user foreign key (user_id) references user(id),
                                              constraint fk_authority foreign key (authority_id) references authority(id)
);