-- Sample parking slots
INSERT INTO parking_slots (slot_number, slot_type, is_occupied, reserved_until) VALUES 
('A1', 'COMPACT', FALSE, NULL),
('A2', 'REGULAR', FALSE, NULL),
('A3', 'LARGE', TRUE, NULL),
('A4', 'ELECTRIC', FALSE, TIMESTAMPADD(HOUR, 1, CURRENT_TIMESTAMP)),
('B1', 'REGULAR', FALSE, NULL);

-- Sample parking tickets (1 active, 1 completed)
INSERT INTO parking_tickets (license_plate, entry_time, exit_time, fee_charged, slot_number) VALUES
('MH12AB1234', CURRENT_TIMESTAMP, NULL, NULL, 'A3'),
('DL09XY7890', TIMESTAMPADD(HOUR, -2, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, 20.00, 'B1');



INSERT INTO users (username, password) VALUES ('alice', '$2a$12$Ok/OOEOmb0mo11UILZWHAeEpLG0DPdDjZ97eMdVTidbPZq6lF0E6u');--password123
INSERT INTO users (username, password) VALUES ('bob', '$2a$12$Ok/OOEOmb0mo11UILZWHAeEpLG0DPdDjZ97eMdVTidbPZq6lF0E6u');



INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');


INSERT INTO user_roles (user_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role_name) VALUES (2, 'ROLE_USER');
