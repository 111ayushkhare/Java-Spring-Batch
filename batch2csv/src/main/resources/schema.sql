CREATE TABLE IF NOT EXISTS employee(
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(100),
     department VARCHAR(50),
     salary DECIMAL(10,2)
);