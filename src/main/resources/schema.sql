CREATE TABLE IF NOT EXISTS parking_slots (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    slot_number VARCHAR(20) NOT NULL,
    slot_type VARCHAR(20),
    is_occupied BOOLEAN,
    occupied_by_license_plate VARCHAR(20),
    occupied_at TIMESTAMP,
    reserved_until TIMESTAMP
);

CREATE TABLE IF NOT EXISTS parking_tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(20) NOT NULL,
    entry_time TIMESTAMP,
    exit_time TIMESTAMP,
    fee_charged DECIMAL(10,2),
    slot_number VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);



CREATE TABLE IF NOT EXISTS role (
    name VARCHAR(50) PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT,
    role_name VARCHAR(50),
    PRIMARY KEY (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_name) REFERENCES role(name)
);