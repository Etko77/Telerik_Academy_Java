DROP DATABASE IF EXISTS university_v1;
CREATE DATABASE university_v1;
USE university_v1;

CREATE TABLE student_courses (
    teacher_email VARCHAR(100),
    student_year INT,
    grade DECIMAL(3,1),
    course_title VARCHAR(100),
    student_id INT,
    exam_date DATE,
    teacher_name VARCHAR(100),
    student_name VARCHAR(100),
    course_id INT,
    credits INT,
    teacher_department VARCHAR(100),
    semester INT,
    faculty_number VARCHAR(20)
);

INSERT INTO student_courses VALUES
('hristov@uni.bg', 1, 5.5, 'Databases',         1, '2024-06-10', 'Dr. Hristov',  'Anna Petrova',     1, 5, 'Computer Science', 2, 'F12345'),
('kirova@uni.bg', 1, 5.0, 'Java Programming',  1, '2024-06-15', 'Prof. Kirova', 'Anna Petrova',     2, 6, 'Software Engineering', 2, 'F12345'),
('hristov@uni.bg', 2, 4.0, 'Databases',         2, '2024-06-10', 'Dr. Hristov',  'Georgi Ivanov',    1, 5, 'Computer Science', 2, 'F22346'),
('hristov@uni.bg', 2, 5.5, 'Operating Systems', 2, '2024-07-01', 'Dr. Hristov',  'Georgi Ivanov',    3, 4, 'Computer Science', 3, 'F22346'),
('kirova@uni.bg', 3, 6.0, 'Java Programming',  3, '2024-06-15','Prof. Kirova', 'Maria Dimitrova',  2, 6, 'Software Engineering',2,'F32347'),
('staneva@uni.bg', 3, 5.0, 'Algorithms',       3, '2024-07-20','Prof. Staneva','Maria Dimitrova',  5, 6, 'Algorithms', 5, 'F32347'),
('velikov@uni.bg', 1, 3.5, 'Networks',         4, '2024-08-10','Dr. Velikov',  'Petar Stoyanov',   4, 3, 'Networking', 4, 'F12348'),
('hristov@uni.bg', 4, 4.5, 'Databases',         5, '2024-06-10','Dr. Hristov',  'Ivana Koleva',     1, 5, 'Computer Science', 2, 'F42349'),
('kirova@uni.bg', 2, 5.0, 'Java Programming',  6, '2024-06-15','Prof. Kirova', 'Stoyan Georgiev',  2, 6, 'Software Engineering',2,'F22350'),
('hristov@uni.bg', 2, 3.0, 'Operating Systems', 6, '2024-07-01','Dr. Hristov',  'Stoyan Georgiev',  3, 4, 'Computer Science', 3, 'F22350'),
('kirova@uni.bg', 3, 4.0, 'Web Development',   7, '2024-08-25','Prof. Kirova', 'Nikola Hristov',   6, 5, 'Software Engineering',6,'F32351'),
('kirova@uni.bg', 3, 5.5, 'Java Programming',  7, '2024-06-15','Prof. Kirova', 'Nikola Hristov',   2, 6, 'Software Engineering',2,'F32351'),
('hristov@uni.bg', 2, 5.0, 'Databases',         8, '2024-06-10','Dr. Hristov',  'Elena Todorova',   1, 5, 'Computer Science', 2, 'F22352'),
('velikov@uni.bg', 2, 4.0, 'Networks',          8, '2024-08-10','Dr. Velikov',  'Elena Todorova',   4, 3, 'Networking', 4, 'F22352'),
('staneva@uni.bg', 1, 5.5, 'Algorithms',        9, '2024-07-20','Prof. Staneva','Milen Petrov',     5, 6, 'Algorithms', 5, 'F12353'),
('velikov@uni.bg', 1, 6.0, 'Web Development',   9, '2024-08-25','Dr. Velikov',  'Milen Petrov',     6, 5, 'Networking', 6, 'F12353'),
('kirova@uni.bg', 4, 3.5, 'Java Programming',  10,'2024-06-15','Prof. Kirova', 'Diana Krasteva',   2, 6, 'Software Engineering',2,'F42354'),
('hristov@uni.bg', 4, 4.5, 'Operating Systems', 10,'2024-07-01','Dr. Hristov', 'Diana Krasteva',   3, 4, 'Computer Science',3,'F42354');