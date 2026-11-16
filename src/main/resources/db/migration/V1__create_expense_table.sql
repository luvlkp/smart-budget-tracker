CREATE TABLE expense(
    Id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255), 
    category VARCHAR(255),
    price DOUBLE PRECISION NOT NULL,
    date DATE
);