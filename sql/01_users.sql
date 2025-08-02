create table users
(
    id          bigint auto_increment
        primary key,
    uuid        varchar(255) not null,
    name        varchar(255) null,
    email       varchar(255) null,
    password    varchar(255) null,
    provider_id varchar(255) null,
    provider    varchar(255) null
);

create index users_provider_index
    on users (provider_id, provider);

create index users_uuid_index
    on users (uuid);

INSERT INTO cmarket.users (id, uuid, name, email, password, provider_id, provider) VALUES (3, 'f7c6e320-8d6f-4e59-b8d9-4a0c19dbd29d', 'Chrisanto', 'c@gmail.com', '$2a$10$icriy4ruUDs9GX8rswruwuzvfHGdEj01gYedbH.XzshUSc5Eo04.q', null, null);
INSERT INTO cmarket.users (id, uuid, name, email, password, provider_id, provider) VALUES (6, '31027ca5-4740-4159-a44b-229824fb8d3d', 'Clovinlee', null, null, 'github', '69330330');
