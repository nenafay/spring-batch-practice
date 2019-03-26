DROP TABLE students IF EXISTS;

CREATE TABLE students (
    student_data BIGINT IDENTITY NOT NULL PRIMARY KEY,
    classroom_id VARCHAR(20),
    student_id VARCHAR(20),
    first_name VARCHAR(20),
    middle_init VARCHAR(1),
    last_name VARCHAR(20)
);