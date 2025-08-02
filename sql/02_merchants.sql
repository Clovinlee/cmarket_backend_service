create table merchants
(
    id         bigint auto_increment
        primary key,
    name       varchar(255)                       null,
    deleted_at datetime default CURRENT_TIMESTAMP not null,
    color      varchar(255)                       null,
    level      bigint                             null
);

INSERT INTO cmarket.merchants (id, name, deleted_at, color, level) VALUES (1, 'Local Merchant', '2024-04-22 03:34:23', '4CAF50', 1);
INSERT INTO cmarket.merchants (id, name, deleted_at, color, level) VALUES (2, 'Traveling Merchant', '2024-04-22 03:34:31', 'FFC107', 2);
INSERT INTO cmarket.merchants (id, name, deleted_at, color, level) VALUES (3, 'Black Merchant', '2024-04-22 03:34:47', '212121', 3);
