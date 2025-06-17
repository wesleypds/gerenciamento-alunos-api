CREATE SCHEMA IF NOT EXISTS aplication;

CREATE TABLE aplication.student_tb (
    id BIGINT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    enrollment VARCHAR(20) NOT NULL,
    course TEXT,
    status TEXT NOT NULL,
    shift TEXT
);

CREATE TABLE aplication.user_tb (
    id BIGINT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    username VARCHAR(20) NOT NULL
);