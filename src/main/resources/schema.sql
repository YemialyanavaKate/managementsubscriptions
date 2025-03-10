CREATE TABLE USERS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (255) NOT NULL,
    created TIMESTAMP,
    updated BOOLEAN
);
CREATE TABLE SUBSCRIPTIONS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    service VARCHAR (255) NOT NULL
);
CREATE TABLE USERS_SUBSCRIPTIONS (
    users_id INT,
    subscriptions_id INT,
    PRIMARY KEY (users_id, subscriptions_id),
    FOREIGN KEY (users_id) REFERENCES USERS(id),
    FOREIGN KEY (subscriptions_id) REFERENCES SUBSCRIPTIONS(id)
);
