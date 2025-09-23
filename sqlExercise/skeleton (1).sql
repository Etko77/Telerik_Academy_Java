-- 1. Write a SQL query to find the average salary of employees who have been hired before year 2000 incl. Round it down to the nearest integer.
SELECT FLOOR(AVG(employees.salary)) AS average_salary_floor FROM employees WHERE YEAR(hire_date)<=2000;
-- 2. Write a SQL query to find all town names that end with a vowel (a,o,u,e,i).
-- Hint: MySQL right() function
SELECT towns.name FROM towns WHERE RIGHT(name,1) IN ('A','O','U','E','I');
-- 3. Write a SQL query to find all town names that start with a vowel (a,o,u,e,i).
SELECT towns.name FROM towns WHERE left(name,1) IN ('A','O','U','E','I');
-- 4. Write a SQL query that outputs the name and the length of the town with the longest name.
SELECT towns.name, LENGTH(towns.name) AS town_name_length FROM towns ORDER BY LENGTH(name) DESC LIMIT 1;
-- 5. Write a SQL query that outputs the name and the length of the town with the shortest name.
SELECT towns.name, LENGTH(towns.name) AS town_name_length FROM towns ORDER BY LENGTH(name) LIMIT 1;
-- 6. Write a SQL query to find all employees with even IDs.
SELECT * FROM employees WHERE employee_id%2 = 0;
-- 7. Write a SQL query to find the names and salaries of the employees that take the minimal salary in the company.
SELECT CONCAT(employees.first_name,' ',employees.last_name)
    AS names,employees.salary FROM employees
    WHERE salary=(SELECT MIN(salary) FROM employees);
-- 8. Write a SQL query to find the names and salaries of the employees that have a salary that is up to 10% higher than the minimal salary for the company.
SELECT CONCAT(employees.first_name,' ',employees.last_name)
           AS names,employees.salary FROM employees
        WHERE salary<(SELECT MIN(salary)*1.1 FROM employees) AND salary > (SELECT MIN(salary) FROM employees);
-- 9. Write a SQL query to find the full name, salary and department of the employees that take the minimal salary in their department.
SELECT CONCAT(e.first_name,' ',e.last_name)
           AS names,
    e.salary,d.name
FROM employees e JOIN departments d ON e.department_id = d.department_id
    WHERE e.salary = (SELECT MIN(e2.salary) FROM employees e2 WHERE e.department_id = e2.department_id);
-- 10. Write a SQL query to find the average salary in department #1.
SELECT FLOOR(AVG(e.salary)) AS average_salary,d.name FROM employees e
    JOIN departments d ON e.department_id = d.department_id WHERE d.department_id = 1;
-- 11. Write a SQL query to find the average salary in the "Sales" department.
SELECT FLOOR(AVG(e.salary)) AS average_salary,d.name FROM employees e
    JOIN departments d ON e.department_id = d.department_id
    WHERE d.name= 'Sales';
-- 12. Write a SQL query that outputs the number of employees in the "Sales" department.
SELECT COUNT(first_name) AS number_of_employees,d.name FROM employees e JOIN departments d ON e.department_id = d.department_id
WHERE d.name= 'Sales';
-- 13. Write a SQL query that outputs the number of employees that have manager.
SELECT COUNT(employees.employee_id) FROM employees WHERE manager_id IS NOT null;
-- 14. Write a SQL query that outputs the number of employees that don't have manager.
SELECT COUNT(employees.employee_id) FROM employees WHERE manager_id IS  null;
-- 15. Write a SQL query to find all departments, along with the average salary for each of them.
SELECT FLOOR(AVG(e.salary)) AS avg_salary,d.name FROM employees e join departments d
    ON e.department_id = d.department_id GROUP BY d.name;
-- 16. Write a SQL query to find all projects that took less than 1 year (365 days) to complete.
-- Hint: Datediff
SELECT * FROM projects WHERE DATEDIFF(end_date,start_date)<365;
-- 17. Write a SQL query that outputs the number for employees from each town, grouped by department. For example how many people from Bellevue work in Sales. How many people from Calgary work in Marketing, and so on...
SELECT COUNT(*) AS count,t.name, d.name
FROM employees e
    JOIN departments d ON e.department_id = d.department_id
    JOIN addresses a ON e.address_id = a.address_id
    JOIN towns t ON a.town_id = t.town_id
    GROUP BY d.name,t.name, d.name;
-- 18. Write a SQL query that outputs the first and last name of all managers that have exactly 5 employees.
SELECT CONCAT(m.first_name," ",m.last_name)AS manager_names,COUNT(e.employee_id) AS number_of_employees  FROM employees e
JOIN employees m ON e.manager_id = m.employee_id GROUP BY m.employee_id HAVING COUNT(e.employee_id) = 5;
-- 19. Write a SQL query to find all employees along with their managers. For employees that do not have manager display the value "(no manager)".
SELECT CONCAT(e.first_name," ",e.last_name) AS employee_names,
       IFNULL(CONCAT(m.first_name," ", m.last_name),'no manager') AS manager_name FROM employees e
    LEFT JOIN employees m ON e.manager_id = m.employee_id;
-- 20. Write a SQL query that outputs the names of all employees whose last name is exactly 5 characters long.
-- Hint: MySQL length
SELECT CONCAT(employees.first_name," ",employees.last_name) AS names FROM employees WHERE LENGTH(last_name)=5;
-- 21. Write a SQL query that outputs the current date and time in the following format "`day.month.year hour:minutes:seconds:milliseconds`".
SELECT CONCAT(DAY(NOW()),'.',MONTH(now()),'.',YEAR(now()),' ',time_format(NOW(),'%h:%i:%s:%f'));
-- 22. Write a SQL query to display the average employee salary by department and job title. For example, the average salary in Engineering for Design Engineer is 32,700.
-- Hint: MySQL date functions
SELECT AVG(e.salary) AS avg_salary,d.name,e.job_title FROM employees e
JOIN departments d ON e.department_id = d.department_id GROUP BY d.department_id, e.job_title;
-- 23. Write a SQL query that outputs the town with most employees.
SELECT t.name, COUNT(e.employee_id) AS number_of_employees FROM employees e
JOIN addresses a on e.address_id = a.address_id
JOIN towns t on a.town_id = t.town_id
GROUP BY t.name
ORDER BY number_of_employees DESC LIMIT 1;
-- 24. Write a SQL query that outputs the number of managers from each town.
SELECT t.name AS town_name,COUNT(e.employee_id) AS total_managers FROM employees e
JOIN addresses a ON e.address_id = a.address_id
JOIN towns t ON a.town_id = t.town_id
WHERE e.employee_id IN (SELECT DISTINCT(employees.manager_id) FROM employees WHERE manager_id IS NOT NULL)
GROUP BY t.name;


-- 25. Write a SQL query to find the manager who is in charge of the most employees and his average salary.
SELECT CONCAT(m.first_name,' ',m.last_name) AS manager_names,COUNT(e.employee_id) AS employees_count FROM employees e
    JOIN employees m ON e.manager_id = m.employee_id group by m.employee_id ORDER BY employees_count DESC LIMIT 1;
-- 26. Write a SQL query that outputs the names of the employees who have worked on the most projects.
SELECT
    CONCAT(e.first_name, ' ', e.last_name) AS names,
    COUNT(p.project_id) AS project_count
FROM employees e
         JOIN employees_projects ep
              ON e.employee_id = ep.employee_id
         JOIN projects p
              ON ep.project_id = p.project_id
GROUP BY e.employee_id, e.first_name, e.last_name
HAVING COUNT(p.project_id) = (
    SELECT MAX(project_count)
    FROM (
             SELECT COUNT(*) AS project_count
             FROM employees_projects
             GROUP BY employee_id
         ) AS sub
)
ORDER BY project_count DESC;
