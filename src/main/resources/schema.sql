CREATE TABLE USERS (
    id INT PRIMARY KEY,
    name VARCHAR (255) not NULL,
    email VARCHAR (255) not NULL,
    created TIMESTAMP,
    updated BOOLEAN
);
CREATE TABLE SUBSCRIPTIONS (
    id INT PRIMARY KEY,
    service VARCHAR (255) not NULL
);