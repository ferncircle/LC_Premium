The Employee table holds all employees. The employee table has three columns: Employee Id, Company Name, and Salary.

+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|1    | A          | 2341   |
|2    | A          | 341    |
|3    | A          | 15     |
|4    | A          | 15314  |
|5    | A          | 451    |
|6    | A          | 513    |
|7    | B          | 15     |
|8    | B          | 13     |
|9    | B          | 1154   |
|10   | B          | 1345   |
|11   | B          | 1221   |
|12   | B          | 234    |
|13   | C          | 2345   |
|14   | C          | 2645   |
|15   | C          | 2645   |
|16   | C          | 2652   |
|17   | C          | 65     |
+-----+------------+--------+
Write a SQL query to find the median salary of each company. Bonus points if you can solve it without using any built-in SQL functions.

+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|5    | A          | 451    |
|6    | A          | 513    |
|12   | B          | 234    |
|9    | B          | 1154   |
|14   | C          | 2645   |
+-----+------------+--------+
Hide Company Tags Google
Show Similar Problems

























Solution:
this is pretty slow, but has been accepted.
The idea is that the difference between the number of employees below and above in terms of salary either results in 0 (when odd number of employees work at the company)
 or 1 (when even number of employees work at the company).
Another key idea is that when you have equal salaries, you still need an ordering to be defined in order to have the numbers add up - the only thing I could use was the
 Id of the employees as a secondary order.

select * from Employee a 
where 
    1 >= abs((select count(*) from Employee b where a.company = b.company and a.salary > b.salary or (a.salary = b.salary and a.id > b.id)) - 
    (select count(*) from Employee b where a.company = b.company and a.salary < b.salary or (a.salary = b.salary and a.id < b.id)))
    
    
    
    
    
    
    
Solution 2:
select T1.Id, T1.Company, T1.Salary
from 
(select count(t2.id) as c1, t1.id, t1.Salary, t1.Company
from Employee as t1 left join Employee as t2
on (t1.Salary < t2.Salary or t1.Salary = t2.Salary and t1.Id < t2.Id) and t1.Company = t2.Company
group by t1.id
) as T1 
join
(select count(t2.id) as c2, t1.id, t1.Salary, t1.Company
from Employee as t1 left join Employee as t2
on (t1.Salary > t2.Salary or t1.Salary = t2.Salary and t1.Id > t2.Id) and t1.Company = t2.Company
group by t1.id) as T2 
on T1.id = T2.id
join
(select count(id) as c3, Company
from Employee
group by Company) as T3 
on T1.Company = T3.Company
where T1.c1 <= T3.c3/2 and T2.c2 <= T3.c3/2
order by Company, Salary  
    
    
  