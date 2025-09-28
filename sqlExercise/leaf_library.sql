DROP DATABASE IF EXISTS leaf_library;
CREATE DATABASE leaf_library;
USE leaf_library;

CREATE TABLE authors (
        author_id INT AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(100) NOT NULL,
        last_name  VARCHAR(100) NOT NULL
);

CREATE TABLE books (
        book_id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        isbn VARCHAR(20) UNIQUE NOT NULL,
        genre VARCHAR(100),
        published_year YEAR
);

CREATE TABLE book_authors (
        book_id INT NOT NULL,
        author_id INT NOT NULL,
        PRIMARY KEY (book_id, author_id),
        FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE,
        FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE
);

CREATE TABLE copies (
        copy_id INT AUTO_INCREMENT PRIMARY KEY,
        book_id INT NOT NULL,
        shelf_code VARCHAR(50) NOT NULL,
        is_available BOOLEAN DEFAULT TRUE,
        FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

CREATE TABLE members (
        member_id INT AUTO_INCREMENT PRIMARY KEY,
        full_name VARCHAR(200) NOT NULL,
        email VARCHAR(150) UNIQUE NOT NULL,
        join_date DATE NOT NULL DEFAULT (CURRENT_DATE)
);

CREATE TABLE loans (
        loan_id INT AUTO_INCREMENT PRIMARY KEY,
        copy_id INT NOT NULL,
        member_id INT NOT NULL,
        loan_date DATE NOT NULL DEFAULT (CURRENT_DATE),
        due_date DATE NOT NULL DEFAULT (DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY)),
        return_date DATE,
        FOREIGN KEY (copy_id) REFERENCES copies(copy_id),
        FOREIGN KEY (member_id) REFERENCES members(member_id)
);
DELIMITER //
CREATE TRIGGER check_max_loans
    BEFORE INSERT ON loans
    FOR EACH ROW
BEGIN
    DECLARE active_loans INT;
    SELECT COUNT(*) INTO active_loans
    FROM loans
    WHERE member_id = NEW.member_id AND return_date IS NULL;

    IF active_loans >= 5 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Member cannot borrow more than 5 books at a time';
    END IF;
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER check_copy_availability
    BEFORE INSERT ON loans
    FOR EACH ROW
BEGIN
    DECLARE available BOOLEAN;
    SELECT is_available INTO available FROM copies WHERE copy_id = NEW.copy_id;

    IF available = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Copy is already loaned out';
    ELSE
        UPDATE copies SET is_available = FALSE WHERE copy_id = NEW.copy_id;
    END IF;
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER return_copy
    AFTER UPDATE ON loans
    FOR EACH ROW
BEGIN
    IF NEW.return_date IS NOT NULL THEN
        UPDATE copies SET is_available = TRUE WHERE copy_id = NEW.copy_id;
    END IF;
END;
//
DELIMITER ;

SELECT loan_id, member_id,
       GREATEST(DATEDIFF(return_date, due_date), 0) * 0.50 AS fine
FROM loans
WHERE return_date IS NOT NULL;
USE leaf_library;

-- ==============================
-- AUTHORS (20)
-- ==============================
INSERT INTO authors (first_name, last_name) VALUES
                                                ('George', 'Orwell'),
                                                ('Aldous', 'Huxley'),
                                                ('J.K.', 'Rowling'),
                                                ('J.R.R.', 'Tolkien'),
                                                ('Harper', 'Lee'),
                                                ('F. Scott', 'Fitzgerald'),
                                                ('Ernest', 'Hemingway'),
                                                ('Jane', 'Austen'),
                                                ('Mark', 'Twain'),
                                                ('Charles', 'Dickens'),
                                                ('Leo', 'Tolstoy'),
                                                ('Fyodor', 'Dostoevsky'),
                                                ('Gabriel', 'Garcia Marquez'),
                                                ('Homer', 'Unknown'),
                                                ('Mary', 'Shelley'),
                                                ('Arthur', 'Conan Doyle'),
                                                ('Agatha', 'Christie'),
                                                ('C.S.', 'Lewis'),
                                                ('Stephen', 'King'),
                                                ('Isaac', 'Asimov');

INSERT INTO books (title, isbn, genre, published_year) VALUES
                                                           ('1984', '9780451524935', 'Dystopian', 1949),
                                                           ('Brave New World', '9780060850524', 'Science Fiction', 1932),
                                                           ('Harry Potter and the Philosopher''s Stone', '9780747532699', 'Fantasy', 1997),
                                                           ('The Lord of the Rings', '9780618640157', 'Fantasy', 1954),
                                                           ('To Kill a Mockingbird', '9780446310789', 'Fiction', 1960),
                                                           ('The Great Gatsby', '9780743273565', 'Fiction', 1925),
                                                           ('The Old Man and the Sea', '9780684801223', 'Fiction', 1952),
                                                           ('Pride and Prejudice', '9780141439518', 'Romance', 1901), -- adjusted
                                                           ('Adventures of Huckleberry Finn', '9780486280615', 'Adventure', 1901), -- adjusted
                                                           ('A Tale of Two Cities', '9781853262647', 'Historical Fiction', 1901), -- adjusted
                                                           ('War and Peace', '9780199232765', 'Historical Fiction', 1901), -- adjusted
                                                           ('Crime and Punishment', '9780140449136', 'Psychological Fiction', 1901), -- adjusted
                                                           ('One Hundred Years of Solitude', '9780060883287', 'Magical Realism', 1967),
                                                           ('The Odyssey', '9780140268867', 'Epic Poetry', 1901), -- adjusted
                                                           ('Frankenstein', '9780486282114', 'Horror', 1901), -- adjusted
                                                           ('Sherlock Holmes: The Complete Novels', '9780553212419', 'Mystery', 1901), -- adjusted
                                                           ('Murder on the Orient Express', '9780062073501', 'Mystery', 1934),
                                                           ('The Chronicles of Narnia', '9780066238500', 'Fantasy', 1950),
                                                           ('The Shining', '9780307743657', 'Horror', 1977),
                                                           ('Foundation', '9780553293357', 'Science Fiction', 1951),
                                                           ('Animal Farm', '9780451526342', 'Satire', 1945),
                                                           ('Emma', '9780141439587', 'Romance', 1901), -- adjusted
                                                           ('David Copperfield', '9780140439441', 'Fiction', 1901), -- adjusted
                                                           ('Anna Karenina', '9780143035008', 'Romance', 1901), -- adjusted
                                                           ('The Brothers Karamazov', '9780374528379', 'Philosophical Fiction', 1901), -- adjusted
                                                           ('Love in the Time of Cholera', '9780307389732', 'Romance', 1985),
                                                           ('The Iliad', '9780140275360', 'Epic Poetry', 1901), -- adjusted
                                                           ('Carrie', '9780307743664', 'Horror', 1974),
                                                           ('It', '9781501142970', 'Horror', 1986),
                                                           ('The Stand', '9780307743688', 'Horror', 1978);
-- ==============================
-- BOOK-AUTHORS relationships
-- (main mappings, 1-to-many or many-to-many)
-- ==============================
INSERT INTO book_authors (book_id, author_id) VALUES
                                                  (1,1), (21,1), -- Orwell
                                                  (2,2), (20,20), -- Huxley, Asimov
                                                  (3,3), -- Rowling
                                                  (4,4), -- Tolkien
                                                  (5,5), -- Harper Lee
                                                  (6,6), -- Fitzgerald
                                                  (7,7), -- Hemingway
                                                  (8,8), (22,8), -- Austen
                                                  (9,9), -- Twain
                                                  (10,10), (23,10), -- Dickens
                                                  (11,11), (24,11), -- Tolstoy
                                                  (12,12), (25,12), -- Dostoevsky
                                                  (13,13), (26,13), -- Marquez
                                                  (14,14), (27,14), -- Homer
                                                  (15,15), -- Shelley
                                                  (16,16), -- Conan Doyle
                                                  (17,17), -- Christie
                                                  (18,18), -- Lewis
                                                  (19,19), (28,19), (29,19), (30,19); -- Stephen King

-- ==============================
-- COPIES (~80 total)
-- ==============================
INSERT INTO copies (book_id, shelf_code) VALUES
                                             (1, 'A-101'), (1, 'A-102'),
                                             (2, 'B-201'),
                                             (3, 'C-301'), (3, 'C-302'), (3, 'C-303'),
                                             (4, 'D-401'), (4, 'D-402'), (4, 'D-403'),
                                             (5, 'E-501'), (5, 'E-502'),
                                             (6, 'F-601'),
                                             (7, 'G-701'),
                                             (8, 'H-801'), (8, 'H-802'),
                                             (9, 'I-901'),
                                             (10, 'J-1001'), (10, 'J-1002'),
                                             (11, 'K-1101'), (11, 'K-1102'), (11, 'K-1103'),
                                             (12, 'L-1201'),
                                             (13, 'M-1301'), (13, 'M-1302'),
                                             (14, 'N-1401'),
                                             (15, 'O-1501'),
                                             (16, 'P-1601'),
                                             (17, 'Q-1701'), (17, 'Q-1702'),
                                             (18, 'R-1801'),
                                             (19, 'S-1901'), (19, 'S-1902'),
                                             (20, 'T-2001'),
                                             (21, 'U-2101'),
                                             (22, 'V-2201'),
                                             (23, 'W-2301'),
                                             (24, 'X-2401'),
                                             (25, 'Y-2501'),
                                             (26, 'Z-2601'),
                                             (27, 'AA-2701'),
                                             (28, 'BB-2801'),
                                             (29, 'CC-2901'),
                                             (30, 'DD-3001'), (30, 'DD-3002');

-- ==============================
-- MEMBERS (25)
-- ==============================
INSERT INTO members (full_name, email, join_date) VALUES
                                                      ('Alice Johnson', 'alice@example.com', '2024-01-15'),
                                                      ('Bob Smith', 'bob@example.com', '2024-02-20'),
                                                      ('Charlie Brown', 'charlie@example.com', '2024-03-05'),
                                                      ('Diana Prince', 'diana@example.com', '2024-04-10'),
                                                      ('Ethan Hunt', 'ethan@example.com', '2024-05-12'),
                                                      ('Fiona Gallagher', 'fiona@example.com', '2024-06-18'),
                                                      ('Gregory House', 'house@example.com', '2024-07-25'),
                                                      ('Hannah Montana', 'hannah@example.com', '2024-08-02'),
                                                      ('Ian Malcolm', 'ian@example.com', '2024-08-15'),
                                                      ('Jack Sparrow', 'jack@example.com', '2024-09-01'),
                                                      ('Kate Winslet', 'kate@example.com', '2024-09-12'),
                                                      ('Luke Skywalker', 'luke@example.com', '2024-09-20'),
                                                      ('Monica Geller', 'monica@example.com', '2024-10-05'),
                                                      ('Ned Stark', 'ned@example.com', '2024-10-18'),
                                                      ('Olivia Benson', 'olivia@example.com', '2024-11-01'),
                                                      ('Peter Parker', 'peter@example.com', '2024-11-15'),
                                                      ('Quinn Fabray', 'quinn@example.com', '2024-11-28'),
                                                      ('Rachel Green', 'rachel@example.com', '2024-12-10'),
                                                      ('Sam Winchester', 'sam@example.com', '2024-12-22'),
                                                      ('Tony Stark', 'tony@example.com', '2025-01-05'),
                                                      ('Uma Thurman', 'uma@example.com', '2025-01-15'),
                                                      ('Victor Hugo', 'victor@example.com', '2025-01-28'),
                                                      ('Walter White', 'walter@example.com', '2025-02-05'),
                                                      ('Xander Harris', 'xander@example.com', '2025-02-20'),
                                                      ('Ygritte Wild', 'ygritte@example.com', '2025-03-01');

SELECT copy_id, book_id, shelf_code FROM copies;
-- ==============================
-- LOANS (~50, mixed active & returned, some late)
-- ==============================
INSERT INTO loans (copy_id, member_id, loan_date, due_date, return_date) VALUES
                                                                             (1, 1, '2025-08-01', '2025-08-15', '2025-08-14'), -- returned on time
                                                                             (2, 2, '2025-08-03', '2025-08-17', '2025-08-25'), -- late
                                                                             (3, 3, '2025-09-01', '2025-09-15', NULL), -- active
                                                                             (4, 4, '2025-09-05', '2025-09-19', NULL), -- active
                                                                             (5, 5, '2025-07-20', '2025-08-03', '2025-08-10'), -- late
                                                                             (6, 6, '2025-09-10', '2025-09-24', NULL),
                                                                             (7, 7, '2025-09-11', '2025-09-25', NULL),
                                                                             (8, 8, '2025-07-01', '2025-07-15', '2025-07-20'), -- late
                                                                             (9, 9, '2025-09-02', '2025-09-16', NULL),
                                                                             (10, 10, '2025-08-15', '2025-08-29', '2025-08-29'), -- on time
                                                                             (11, 11, '2025-09-01', '2025-09-15', NULL),
                                                                             (12, 12, '2025-09-04', '2025-09-18', NULL),
                                                                             (13, 13, '2025-08-01', '2025-08-15', '2025-08-18'), -- late
                                                                             (14, 14, '2025-09-01', '2025-09-15', NULL),
                                                                             (15, 15, '2025-09-05', '2025-09-19', NULL),
                                                                             (16, 16, '2025-07-10', '2025-07-24', '2025-07-23'), -- on time
                                                                             (17, 17, '2025-09-07', '2025-09-21', NULL),
                                                                             (18, 18, '2025-09-08', '2025-09-22', NULL),
                                                                             (19, 19, '2025-09-01', '2025-09-15', NULL),
                                                                             (20, 20, '2025-08-01', '2025-08-15', '2025-08-30'), -- late
                                                                             (21, 21, '2025-09-02', '2025-09-16', NULL),
                                                                             (22, 22, '2025-09-05', '2025-09-19', NULL),
                                                                             (23, 23, '2025-09-06', '2025-09-20', NULL),
                                                                             (24, 24, '2025-08-10', '2025-08-24', '2025-08-28'), -- late
                                                                             (25, 25, '2025-09-01', '2025-09-15', NULL),
                                                                             (26, 1, '2025-09-03', '2025-09-17', NULL),
                                                                             (27, 2, '2025-09-04', '2025-09-18', NULL),
                                                                             (28, 3, '2025-09-05', '2025-09-19', NULL),
                                                                             (29, 4, '2025-08-01', '2025-08-15', '2025-08-14'), -- on time
                                                                             (30, 5, '2025-08-02', '2025-08-16', '2025-08-25'), -- late
                                                                             (31, 6, '2025-09-01', '2025-09-15', NULL),
                                                                             (32, 7, '2025-09-01', '2025-09-15', NULL),
                                                                             (33, 8, '2025-09-01', '2025-09-15', NULL),
                                                                             (34, 9, '2025-09-01', '2025-09-15', NULL),
                                                                             (35, 10, '2025-09-01', '2025-09-15', NULL),
                                                                             (36, 11, '2025-09-01', '2025-09-15', NULL),
                                                                             (37, 12, '2025-09-01', '2025-09-15', NULL),
                                                                             (38, 13, '2025-09-01', '2025-09-15', NULL),
                                                                             (39, 14, '2025-09-01', '2025-09-15', NULL),
                                                                             (40, 15, '2025-09-01', '2025-09-15', NULL),
                                                                             (41, 16, '2025-09-01', '2025-09-15', NULL),
                                                                             (42, 17, '2025-09-01', '2025-09-15', NULL),
                                                                             (43, 18, '2025-09-01', '2025-09-15', NULL),
                                                                             (44, 19, '2025-09-01', '2025-09-15', NULL);

-- Queries--
#1
SELECT b.title AS book_title,CONCAT(a.first_name,' ',a.last_name)AS author FROM books AS b
JOIN book_authors ba on b.book_id = ba.book_id
JOIN authors a on ba.author_id = a.author_id;
#2
SELECT c.copy_id, b.title,
       CASE
           WHEN l.return_date IS NULL AND CURDATE() <= l.due_date THEN 'On loan'
           WHEN l.return_date IS NULL AND CURDATE() > l.due_date THEN 'Overdue'
           ELSE 'Available'
           END AS availability
FROM copies c
         JOIN books b ON c.book_id = b.book_id
         LEFT JOIN loans l ON c.copy_id = l.copy_id
WHERE b.title = '1984';
#3
SELECT b.title, CONCAT(a.first_name,' ',a.last_name)AS author from books AS b
JOIN book_authors ba on b.book_id = ba.book_id
JOIN authors a on ba.author_id = a.author_id
WHERE b.title LIKE '%19%' OR a.last_name= 'Rowling';
#4
SELECT
    m.full_name, b.title, c.copy_id, l.loan_date,l.due_date FROM members as m
JOIN loans l on m.member_id = l.member_id
JOIN copies c on l.copy_id = c.copy_id
JOIN books b on c.book_id = b.book_id
WHERE l.return_date IS NULL;
#5
SELECT
    m.full_name, b.title, c.copy_id, l.loan_date,l.due_date FROM members as m
JOIN loans l on m.member_id = l.member_id
JOIN copies c on l.copy_id = c.copy_id
JOIN books b on c.book_id = b.book_id
WHERE l.return_date > l.due_date;
#6
SELECT b.title,COUNT(l.loan_id)AS total_loan_count FROM books AS b
JOIN copies c on b.book_id = c.book_id
JOIN loans l on c.copy_id = l.copy_id
        GROUP BY b.title
        ORDER BY total_loan_count DESC
        LIMIT 3;
#7
SELECT DATE_FORMAT(loan_date, '%Y-%m') AS month, COUNT(*) AS loan_count
FROM loans
WHERE loan_date >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH)
GROUP BY DATE_FORMAT(loan_date, '%Y-%m')
ORDER BY month;
#8
SELECT m.full_name AS member
FROM members m
         JOIN loans l ON m.member_id = l.member_id
WHERE l.return_date IS NULL
GROUP BY m.member_id
HAVING COUNT(*) = 5;
#9
SELECT m.full_name AS member
FROM members m
         JOIN loans l ON m.member_id = l.member_id
WHERE l.return_date IS NOT NULL
GROUP BY m.member_id
HAVING AVG(DATEDIFF(l.return_date, l.due_date)) > 2;
#10
SELECT m.full_name AS member,
       b.title,
       DATEDIFF(CURDATE(), l.due_date) AS days_late,
       DATEDIFF(CURDATE(), l.due_date) * 0.50 AS fine_amount
FROM loans l
         JOIN members m ON l.member_id = m.member_id
         JOIN copies c ON l.copy_id = c.copy_id
         JOIN books b ON c.book_id = b.book_id
WHERE l.return_date IS NULL AND CURDATE() > l.due_date;