
-- 1. Select all students
    SELECT * FROM students;
-- 2. Select all courses with more than 4 credits
SELECT * FROM courses WHERE credits > 4;
-- 3. List students from year 2
SELECT * FROM students WHERE year>=2;
-- 4. Show all students ordered by last_name, first_name.
SELECT * FROM students ORDER BY last_name,first_name;
-- 5. Find all students enrolled in 'Databases'
SELECT s.first_name, s.last_name, c.title FROM students s
JOIN enrollments e ON s.id = e.student_id
JOIN courses c ON e.course_id = c.id
WHERE c.title LIKE 'Database';
-- 6. Show all courses and their assigned teachers
SELECT c.title,CONCAT(t.first_name,' ',t.last_name) AS names FROM courses c
JOIN course_teachers ct ON c.id = ct.course_id
JOIN teachers t ON ct.teacher_id = t.id;
-- 7. Count how many students are enrolled in each course
-- 8. Find the average grade per course
-- 9. List courses without enrollments
-- 10. Show students with grade >= 5.5
-- 11. Find the course with the highest number of students
-- 12. Show students and total credits from their enrollments
-- 13. List teachers and courses they teach
-- 14. Find students enrolled in more than 2 courses
-- 15. Find courses where the average grade < 4.5
-- 16. Show the top 3 students by number of enrollments
-- 17. Show grades of students in 'Java Programming'
-- 18. List students not enrolled in any course
-- 19. Find teachers who teach more than one course
-- 20. Show max grade in 'Databases'
-- 21. List students who received the same grade in more than one course
-- 22. Show all students with their grades, ordered by grade desc
-- 23. Show students, their courses, and teachers
-- 24. Find the department teaching the most courses
-- 25. List students enrolled in both 'Databases' and 'Java Programming'
-- 26. Show average grade per student across all courses
-- 27. Show students with the lowest grade overall
-- 28. Show students who passed all their courses (grade >= 3)
-- 29. Show all pairs (student, course, teacher)
-- 30. Find students not enrolled in the most popular course
-- 31. Show number of students per department (via teachers & courses)
-- 32. Show courses taught by multiple teachers
-- 33. List students who never scored below 4.0
-- 34. Show students who scored above the average in 'Algorithms'
-- 35. Find students with more than 15 credits total
-- 36. Show all teachers with their departments and emails
-- 37. List all courses from semester 2
-- 38. Find courses that give maximum credits
-- 39. Show students with exams taken in July 2024
-- 40. Find students with grades better than the average grade in their year
-- 41. List teachers who do not teach any course
-- 42. Find students taking courses from more than one department
-- 43. Show each semester and number of courses in it
-- 44. Find the student with the highest average grade
-- 45. List teachers teaching courses with more than 5 credits
-- 46. Show students and courses where grade < 4.0
-- 47. Find students who share the same courses as 'Anna Petrova'
-- 48. Show average grade per department
-- 49. Show students with their earliest exam date
-- 50. Show courses where no student scored below 5.0
