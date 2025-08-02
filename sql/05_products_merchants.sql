create table products_merchants
(
    id          bigint auto_increment
        primary key,
    id_product  bigint null,
    id_merchant bigint null,
    merchant_id bigint null,
    product_id  bigint null,
    constraint FK8dmtl2qfa8lyfsylfhymrkg95
        foreign key (merchant_id) references merchants (id),
    constraint FKe1cn7ng4ttjtxkad2xhq0j2ko
        foreign key (product_id) references products (id),
    constraint products_merchants_ibfk_1
        foreign key (id_merchant) references merchants (id)
            on delete cascade,
    constraint products_merchants_ibfk_2
        foreign key (id_product) references products (id)
            on delete cascade
);

create index id_merchant
    on products_merchants (id_merchant);

create index id_product
    on products_merchants (id_product);

INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (1, 1, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (2, 2, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (3, 2, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (4, 3, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (5, 3, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (6, 4, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (7, 4, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (8, 5, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (9, 5, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (10, 6, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (11, 6, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (12, 7, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (13, 8, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (14, 9, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (15, 10, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (16, 10, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (17, 11, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (18, 11, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (19, 12, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (20, 4, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (21, 14, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (22, 15, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (23, 16, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (24, 17, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (25, 17, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (26, 18, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (27, 18, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (28, 19, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (29, 20, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (30, 20, 3, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (31, 21, 1, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (32, 21, 2, null, null);
INSERT INTO cmarket.products_merchants (id, id_product, id_merchant, merchant_id, product_id) VALUES (33, 21, 3, null, null);
