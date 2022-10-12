DROP DATABASE IF EXISTS cruise;
CREATE DATABASE IF NOT EXISTS cruise;
USE cruise;

CREATE TABLE IF NOT EXISTS role
(
    role_id INTEGER PRIMARY KEY auto_increment,
    name    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    user_id    INTEGER PRIMARY KEY auto_increment,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    salt       VARCHAR(255) NOT NULL,
    role_id    INTEGER      NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Role (role_id)
);

CREATE TABLE IF NOT EXISTS ships
(
    ship_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    ship_name         VARCHAR(255) NOT NULL,
    passengers        INTEGER      NOT NULL,
    number_of_staff   INTEGER      NOT NULL,
    ship_image_source VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS cabin_types
(
    cabin_type_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    cabin_type_name VARCHAR(255) NOT NULL,
    number_of_beds  INTEGER      NOT NULL,
    price           INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS cabin_status
(
    cabin_status_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    cabin_status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cabins
(
    cabin_id        INTEGER PRIMARY KEY auto_increment,
    cabin_type_id   INTEGER NOT NULL,
    cabin_status_id INTEGER NOT NULL,
    ship_id         INTEGER NOT NULL,
    FOREIGN KEY (ship_id) REFERENCES ships (ship_id) ON DELETE CASCADE,
    FOREIGN KEY (cabin_status_id) REFERENCES cabin_status (cabin_status_id) ON DELETE CASCADE,
    FOREIGN KEY (cabin_type_id) REFERENCES cabin_types (cabin_type_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ports
(
    port_id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    port_name_ua VARCHAR(255) NOT NULL,
    port_name_en VARCHAR(255) NOT NULL,
    country_ua   VARCHAR(255) NOT NULL,
    country_en   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS routes
(
    route_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    route_name VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS route_points
(
    route_point_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    route_id       INTEGER NOT NULL,
    day_number     INTEGER NOT NULL,
    port_id        INTEGER NOT NULL,
    FOREIGN KEY (route_id) REFERENCES routes (route_id) ON DELETE CASCADE,
    FOREIGN KEY (port_id) REFERENCES ports (port_id)
);

CREATE TABLE IF NOT EXISTS cruise_statuses
(
    cruise_status_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    cruise_status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cruises
(
    cruise_id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    cruise_name      VARCHAR(255) NOT NULL,
    route_id         INTEGER      NOT NULL,
    date_start       DATE         NOT NULL,
    date_end         DATE         NOT NULL,
    cruise_status_id INTEGER      NOT NULL,
    ship_id          INTEGER      NOT NULL,
    nights INTEGER AS (datediff(date_end, date_start)),
    FOREIGN KEY (route_id) REFERENCES routes (route_id),
    FOREIGN KEY (cruise_status_id) REFERENCES cruise_statuses (cruise_status_id)
);

CREATE TABLE IF NOT EXISTS order_statuses
(
    order_status_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id        INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id         INTEGER NOT NULL,
    order_date      DATE    NOT NULL,
    order_status_id INTEGER NOT NULL,
    cruise_id       INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (order_status_id) REFERENCES order_statuses (order_status_id) ON DELETE CASCADE,
    FOREIGN KEY (cruise_id) REFERENCES cruises (cruise_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payments
(
    payment_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id     INTEGER NOT NULL,
    payment_date DATE    NOT NULL,
    amount       INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE cabins_orders
(
    cabin_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (cabin_id, order_id),
    INDEX fk_cabins_orders_order_idx (order_id ASC) VISIBLE,
    CONSTRAINT fk_cabins_orders_cabin
        FOREIGN KEY (cabin_id)
            REFERENCES cabins (cabin_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_cabins_orders_order
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);