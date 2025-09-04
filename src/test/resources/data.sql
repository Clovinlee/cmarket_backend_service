-- =============================
-- = INSERT MERCHANTS TABLE      
-- =============================
INSERT INTO merchants (id, name, level, color, deleted_at) VALUES
(1, 'Local Merchant', 1, '4CAF50', NULL),
(2, 'Traveling Merchant', 2, 'FFC107', NULL),
(3, 'Black Merchant', 3, '212121', NULL);

-- =============================
-- = INSERT RARITIES TABLE      
-- =============================
INSERT INTO rarities (id, name, level, color, deleted_at) VALUES
(1, 'Common', 1, 'FFFFFF', NULL),
(2, 'Uncommon', 2, '008000', NULL),
(3, 'Rare', 3, '0000FF', NULL),
(4, 'Epic', 3, '800080', NULL),
(5, 'Legendary', 3, 'FFFF00', NULL);

-- =============================
-- = INSERT PRODUCTS TABLE      
-- =============================
INSERT INTO `products` (id, name, description, price, rarity_id, image, slug) VALUES
(1,'Torch','A simple torch to lighten your path, though it has limited uses',5.00,1,'https://png.pngtree.com/png-vector/20240322/ourmid/pngtree-torch-fire-on-stick-medieval-lamp-and-tool-png-image_12178957.png','torch'),
(2,'Boots of Swiftness','Enable you to walk in stealth',109.00,2,'https://r2.easyimg.io/rl532krro/boots.png','boots-of-swiftness'),
(3,'Necklace of Mana','Increase your mana capacity and regeneration',281.00,3,'https://r2.easyimg.io/st94uelvw/amulet.png','necklace-of-mana'),
(4,'Cape of Invisibility','Turn you invisible for set amount of time',877.00,4,'https://static.miraheze.org/criticalrolewiki/thumb/5/5e/Cabal%27s_Ruin_-_Jessica_Nguyen.png/800px-Cabal%27s_Ruin_-_Jessica_Nguyen.png','cape-of-invisibility'),
(5,'Ring of Goliath','This ring is imbued with giant magic that can turn you into Giant with giant''s strength.',468.00,5,'https://upload.wikimedia.org/wikipedia/commons/6/6e/One_Ring.png','ring-of-goliath'),
(6,'Oil Lamp','Longer lasting than torch',30.00,1,NULL,'oil-lamp'),
(7,'Ring of Cypher','Enable you to decypher old text',89.00,2,NULL,'ring-of-cypher'),
(8,'Frost Walker Boots','Makes any water you touch with your boots to turn into ice.',399.00,3,'https://png.pngtree.com/png-vector/20240203/ourmid/pngtree-3d-render-of-blue-boots-png-image_11535805.png','frost-walker-boots'),
(9,'Gem of Miranda','Remains of Magician Miranda',290.00,4,NULL,'gem-of-miranda'),
(10,'Mask of Azaroth','Infamous mask once worn by Azaroth, the elder dragon.',999.00,5,'https://png.pngtree.com/png-vector/20230903/ourmid/pngtree-fantasy-mask-cutout-png-file-png-image_9953716.png','mask-of-azaroth');

-- =============================
-- = INSERT PRODUCTS_MERCHANTS TABLE      
-- =============================
INSERT INTO `products_merchants` (id, product_id, merchant_id) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2),
(4, 2, 3),
(5, 3, 1),
(6, 3, 3),
(7, 4, 1),
(8, 5, 2),
(9, 6, 3),
(10, 7, 1),
(11, 8, 2),
(12, 9, 3),
(13, 10, 1),
(14, 10, 2);