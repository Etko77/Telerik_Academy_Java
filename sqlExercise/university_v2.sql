DROP DATABASE IF EXISTS university_v2;
CREATE DATABASE university_v2;
USE university_v2;

-- Departments table
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Titles table 
CREATE TABLE titles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Students table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    faculty_number VARCHAR(20) UNIQUE
);

-- Courses table
CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    semester INT NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Teachers table 
CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Teacher <-> Title relation 
CREATE TABLE teacher_titles (
    teacher_id INT,
    title_id INT,
    PRIMARY KEY (teacher_id, title_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (title_id) REFERENCES titles(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Enrollments table
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    grade DECIMAL(3,1),
    exam_date DATE,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Course-Teachers relation
CREATE TABLE course_teachers (
    course_id INT,
    teacher_id INT,
    PRIMARY KEY (course_id, teacher_id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);



-- Departments
INSERT INTO departments (name) VALUES
 ('Computer Science'),
 ('Software Engineering'),
 ('Networking'),
 ('Algorithms');

-- Titles
INSERT INTO titles (name) VALUES
 ('Doctor'),
 ('Docent.'),
 ('Professor.');

-- Students (
INSERT INTO students (first_name, last_name, year, faculty_number) VALUES
 ('Anna','Petrova', 1, 'F12345'),
 ('Georgi','Ivanov', 2, 'F22346'),
 ('Maria','Dimitrova', 3, 'F32347'),
 ('Petar','Stoyanov', 1, 'F12348'),
 ('Ivana','Koleva', 4, 'F42349'),
 ('Stoyan','Georgiev', 2, 'F22350'),
 ('Nikola','Hristov', 3, 'F32351'),
 ('Elena','Todorova', 2, 'F22352'),
 ('Milen','Petrov', 1, 'F12353'),
 ('Diana','Krasteva', 4, 'F42354');

-- Courses 
INSERT INTO courses (title, credits, semester, department_id) VALUES
 ('Databases', 5, 2, 1),           -- Computer Science
 ('Java Programming', 6, 2, 2),    -- Software Engineering
 ('Operating Systems', 4, 3, 1),   -- Computer Science
 ('Networks', 3, 4, 3),            -- Networking
 ('Algorithms', 6, 5, 4),          -- Algorithms
 ('Web Development', 5, 6, 2);     -- Software Engineering

-- Teachers
INSERT INTO teachers (first_name, last_name, email, department_id) VALUES
 ('Ivan', 'Hristov', 'hristov@uni.bg', 1),  
 ('Maria', 'Kirova', 'kirova@uni.bg', 2),   
 ('Petar', 'Velikov', 'velikov@uni.bg', 3), 
 ('Elena', 'Staneva', 'staneva@uni.bg', 4);  

-- Teacher <-> Title links 
INSERT INTO teacher_titles (teacher_id, title_id) VALUES
 (1, 1), 
 (2, 3), 
 (3, 1),
 (4, 3); 

-- Enrollments
INSERT INTO enrollments (student_id, course_id, grade, exam_date) VALUES
 (1,1,5.5,'2024-06-10'), (1,2,5.0,'2024-06-15'),
 (2,1,4.0,'2024-06-10'), (2,3,5.5,'2024-07-01'),
 (3,2,6.0,'2024-06-15'), (3,5,5.0,'2024-07-20'),
 (4,4,3.5,'2024-08-10'), (5,1,4.5,'2024-06-10'),
 (6,2,5.0,'2024-06-15'), (6,3,3.0,'2024-07-01'),
 (7,6,4.0,'2024-08-25'), (7,2,5.5,'2024-06-15'),
 (8,1,5.0,'2024-06-10'), (8,4,4.0,'2024-08-10'),
 (9,5,5.5,'2024-07-20'), (9,6,6.0,'2024-08-25'),
 (10,2,3.5,'2024-06-15'), (10,3,4.5,'2024-07-01');

-- Course-Teachers
INSERT INTO course_teachers (course_id, teacher_id) VALUES
 (1,1), (2,2), (3,1), (4,3), (5,4), (6,2), (6,3);
