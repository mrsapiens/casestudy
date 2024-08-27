INSERT INTO admin_user (username, password, authority) VALUES ('ADMIN', '$2a$10$m2PcJpFhOb.2mK.PHQmwdu04aybm/xgQcCWjbpmTHLzjRUv9N5nY6', 'ADMIN');
INSERT INTO customer (username, password, balance, authority) VALUES ('user1', '$2a$10$LRIO5Sxett7bhwt9tlCyS.EyG3HyhynbE36lVAA6Qayo7lvWnc5Ci', 0,'INDIVIDUAL');
INSERT INTO customer (username, password, balance, authority) VALUES ('user2', '$2a$10$LRIO5Sxett7bhwt9tlCyS.EyG3HyhynbE36lVAA6Qayo7lvWnc5Ci', 0,'INDIVIDUAL');
INSERT INTO asset (asset_name, usable_size, asset_size, customer_id) VALUES ('asset1', 100, 100, SELECT min(id) from customer);
INSERT INTO asset (asset_name, usable_size, asset_size, customer_id) VALUES ('asset2', 100, 100, SELECT max(id) from customer);