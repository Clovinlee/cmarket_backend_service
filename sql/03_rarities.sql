create table rarities
(
    id         bigint auto_increment
        primary key,
    name       varchar(255)                  not null,
    level      bigint                        not null,
    color      varchar(255) default 'FFFFFF' not null,
    deleted_at datetime                      null,
    id_rarity  varchar(255)                  null,
    constraint name
        unique (name)
);

INSERT INTO cmarket.rarities (id, name, level, color, deleted_at, id_rarity) VALUES (1, 'Common', 1, 'FFFFFF', null, null);
INSERT INTO cmarket.rarities (id, name, level, color, deleted_at, id_rarity) VALUES (2, 'Uncommon', 2, '008000', null, null);
INSERT INTO cmarket.rarities (id, name, level, color, deleted_at, id_rarity) VALUES (3, 'Rare', 3, '0000FF', null, null);
INSERT INTO cmarket.rarities (id, name, level, color, deleted_at, id_rarity) VALUES (4, 'Epic', 4, '800080', null, null);
INSERT INTO cmarket.rarities (id, name, level, color, deleted_at, id_rarity) VALUES (5, 'Legendary', 5, 'FFFF00', null, null);
