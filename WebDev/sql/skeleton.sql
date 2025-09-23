-- 1. Write a SQL query that outputs all information about all departments.
SELECT * FROM departments;
-- 2. Write a SQL query that outputs all department names.
SELECT name from departments;
-- 3. Write a SQL query that outputs first and last name of each employee, along with their salary.
SELECT employees.first_name, employees.last_name, employees.salary FROM employees;
-- 4. Write a SQL query that outputs the full name of each employee.
SELECT CONCAT(employees.first_name," ",employees.last_name) AS "full name" FROM employees;
-- 5. Write a SQL query to generate an email addresses for each employee. Consider that the email domain is company.com. For example, John Doe's email would be "John.Doe@company.com". The produced column should be named "Full Email Addresses".
SELECT CONCAT(employees.first_name,".",employees.last_name,"@company.com") AS email_address FROM employees;
-- 6. Write a SQL query to find all the different employee salaries.
SELECT DISTINCT employees.salary from employees;
-- 7. Write a SQL query that outputs all information about the employees whose job title is "Sales Representative".
SELECT * from employees where job_title="Sales Representative";
-- 8. Write an SQL query to find all employees who have a salary that is bigger than their manager's.
SELECT e.employee_id, e.first_name, e.last_name, e.salary AS employee_salary,
       m.first_name, m.last_name, m.salary AS manager_salary
FROM employees e JOIN  employees m ON e.manager_id = m.employee_id
WHERE  e.salary > m.salary;
-- 9. Write a SQL query to find the names of all employees whose first name starts with "SA".
SELECT CONCAT(employees.first_name," ",employees.last_name) AS "full name" FROM employees  WHERE first_name LIKE 'SA%';
-- 10. Write a SQL query to find the names of all employees whose last name contains "ei".
SELECT CONCAT(employees.first_name," ",employees.last_name) AS "full name" FROM employees  WHERE last_name LIKE '%ei%';
-- 11. Write a SQL query to find all employees whose salary is in the range [20000…30000].
SELECT CONCAT(employees.first_name," ",employees.last_name) AS "full name" FROM employees  WHERE salary >= 20000 AND salary<=30000;
-- 12. Write a SQL query to find the names of all employees whose salary is 25000, 14000, 12500 or 23600.
SELECT CONCAT(employees.first_name," ",employees.last_name) AS "full name" FROM employees  WHERE salary = 25000 OR salary = 1400 OR salary = 12500 OR salary = 23600;
-- 13. Write a SQL query to find all employees that do not have manager.
SELECT * FROM employees  WHERE manager_id IS NULL;
-- 14. Write a SQL query to find the names of all employees who were hired before their managers.
SELECT e.employee_id, e.first_name, e.last_name, e.hire_date AS employee_hireDate,
       m.first_name, m.last_name, m.hire_date AS manager_hireDate
FROM employees e JOIN  employees m ON e.manager_id = m.employee_id
WHERE  e.hire_date <  m.hire_date;
-- 15. Write a SQL query to find all employees whose salary is more than 50000. Order them in decreasing order, based on their salary.
SELECT * FROM employees WHERE salary > 50000 ORDER BY salary DESC;
-- 16. Write a SQL query to find the top 5 best paid employees.
SELECT * FROM employees WHERE salary > 50000 ORDER BY salary DESC LIMIT 5;
-- 17. Write a SQL query that outputs all employees along their address.

-- 18. Write a SQL query to find all employees whose middle name is the same as the first letter of their town.

-- 19. Write a SQL query that outputs all employees (first and last name) that have a manager, along with their manager (first and last name).

-- 20. Write a SQL query that outputs all employees that have a manager (first and last name), along with their manager (first and last name) and the employee's address.

-- 21. Write a SQL query to find all departments and all town names in a single column.

-- 22. Write a SQL query to find all employees and their manager, along with the employees that do not have manager. If they do not have a manager, output "n/a".

-- 23. Write a SQL query to find the names of all employees from the departments "Sales" AND "Finance" whose hire year is between 1995 and 2005.
SELECT e.first_name,e.last_name, d.name, e.hire_date FROM employees e LEFT JOIN departments d ON d.department_id = e.department_id
WHERE d.name IN ('Sales', 'Finance') AND YEAR(e.hire_date) BETWEEN 1995 AND 2005;