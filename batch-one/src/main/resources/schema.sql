DROP TABLE IF EXISTS processed_employee;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee(
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(100),
       department VARCHAR(50),
       salary DECIMAL(10,2)
);

CREATE TABLE processed_employee(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO employee(name, department, salary) VALUES
                                                   ('Ayush','Engineering',45000),
                                                   ('Rahul','Engineering',60000),
                                                   ('Priya','Engineering',70000),
                                                   ('Neha','HR',52000),
                                                   ('Aman','Engineering',80000),
                                                   ('Rohit','Sales',48000),
                                                   ('Sneha','Engineering',62000);