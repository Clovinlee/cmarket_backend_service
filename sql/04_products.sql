create table products
(
    id          bigint auto_increment
        primary key,
    name        varchar(255)   null,
    description varchar(255)   null,
    price       decimal(38, 2) null,
    id_rarity   bigint         null,
    image       varchar(255)   null,
    slug        varchar(255)   null,
    constraint products_ibfk_1
        foreign key (id_rarity) references rarities (id)
            on delete set null
);

create index id_rarity
    on products (id_rarity);

INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (1, 'Mask of Azaroth', 'Infamous mask once wore by Azaroth, the elder dragon. Nobody knows what it does or how it was created', 999.00, 5, 'https://png.pngtree.com/png-vector/20230903/ourmid/pngtree-fantasy-mask-cutout-png-file-png-image_9953716.png', 'mask-of-azaroth');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (2, 'Boots of Swiftness', 'Enable you to walk in stealth', 109.00, 2, 'https://r2.easyimg.io/rl532krro/boots.png', 'boots-of-swiftness');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (3, 'Gloves of Titan', 'Makes your grip strength into titan like strength. But it doesn\'t last long, so use wisely.', 85.00, 2, 'https://r2.easyimg.io/dofiae3wu/glove.png', 'gloves-of-titan');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (4, 'Necklace of Aragog', 'It contains remnants of Aragog\'s last power during the humanity dark era.', 301.00, 4, null, 'necklace-of-aragog');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (5, 'Pirate Eyewear', 'Makes you look alike pirate', 25.00, 1, null, 'pirate-eyewear');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (6, 'Ring of Void', 'One of the fourth mystical ring left during human dark era. Contains mysterious and unfathomable power', 611.00, 5, null, 'ring-of-void');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (7, 'Shard of Aragog\'s Sowrd', 'One of many shard of Aragog\'s sword. Who knows how many of them are', 999.00, 5, null, 'shard-of-aragogs-sowrd');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (8, 'Ring of Cypher', 'Enable you to decypher old text', 89.00, 2, null, 'ring-of-cypher');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (9, 'Bag of Infinium', 'Hold items more than it looks', 399.00, 4, null, 'bag-of-infinium');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (10, 'Gem of Miranda', 'Remains of Magician Miranda', 290.00, 4, null, 'gem-of-miranda');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (11, 'Cape of Elemental', 'Protects wearer against elemental attacks', 380.00, 4, null, 'cape-of-elemental');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (12, 'Oil Lamp', 'Longer lasting than torch', 30.00, 1, null, 'oil-lamp');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (13, 'Ring of Goliath', 'This ring is imbued with giant magic that can turn you into Giant with giant\'s strength.', 468.00, 5, 'https://upload.wikimedia.org/wikipedia/commons/6/6e/One_Ring.png', 'ring-of-goliath');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (14, 'Torch', 'A simple torch to lighten your path, though it has limited uses', 5.00, 1, 'https://png.pngtree.com/png-vector/20240322/ourmid/pngtree-torch-fire-on-stick-medieval-lamp-and-tool-png-image_12178957.png', 'torch');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (15, 'Necklace of Mana', 'Increase your mana capacity and regeneration', 281.00, 3, 'https://r2.easyimg.io/st94uelvw/amulet.png', 'necklace-of-mana');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (16, 'Glasses of "Knowledge"', 'Makes you look more intelligent than ordinary people', 10.00, 1, 'https://w7.pngwing.com/pngs/648/755/png-transparent-sunglasses-glasses-glass-lens-glasses.png', 'glasses-of-knowledge');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (17, 'Frost Walker Boots', 'Makes any water you touch with your boots to turn into ice, basically you can walk on water.', 399.00, 3, 'https://png.pngtree.com/png-vector/20240203/ourmid/pngtree-3d-render-of-blue-boots-png-image_11535805.png', 'frost-walker-boots');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (18, 'Cape of Invisibility', 'Turn you invisible for set amount of time', 877.00, 4, 'https://static.miraheze.org/criticalrolewiki/thumb/5/5e/Cabal%27s_Ruin_-_Jessica_Nguyen.png/800px-Cabal%27s_Ruin_-_Jessica_Nguyen.png', 'cape-of-invisibility');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (19, 'Ring of Misfortune', 'Brings misfortune to whoever wore this ring', 199.00, 2, 'https://i.pinimg.com/originals/80/60/31/806031647164b83533320f99516ddb41.png', 'ring-of-misfortune');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (20, 'Ring of Fortune', 'Brings fortune to whoever wore this ring', 499.00, 4, 'https://i.pinimg.com/originals/8b/f6/98/8bf698a34c1d55a97516f365cf4d58dd.png', 'ring-of-fortune');
INSERT INTO cmarket.products (id, name, description, price, id_rarity, image, slug) VALUES (21, 'Gloves of Mining', 'Keep those fatigue at bay by increasing your stamina', 35.00, 1, 'https://i.pinimg.com/originals/80/87/8d/80878dac2d0ddbb5f2bde042fc5b173e.png', 'gloves-of-mining');
