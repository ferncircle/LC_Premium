The Numbers table keeps the value of number and its frequency.

+----------+-------------+
|  Number  |  Frequency  |
+----------+-------------|
|  0       |  7          |
|  1       |  1          |
|  2       |  3          |
|  3       |  1          |
+----------+-------------+
In this table, the numbers are 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 3, so the median is (0 + 0) / 2 = 0.

+--------+
| median |
+--------|
| 0.0000 |
+--------+
Write a query to find the median of all numbers and name the result as median.

Hide Company Tags Pinterest
Show Similar Problems
















Solution:

select avg(a.Number) as median
from(
select number, frequency,@row:=@row+0 as beg, (@row:=frequency+@row) as fin,b.total
from (select * from Numbers order by Number) c,
(select @row:=0 ) r,
(select sum(frequency) as total from Numbers) b ) a
where
case when total%2=0 then (total/2 <=a.fin and total/2 >a.beg) or ((total)/2+1 <=a.fin and (total)/2+1 >a.beg)
else (total+1)/2 <=a.fin and (total+1)/2 >a.beg
end