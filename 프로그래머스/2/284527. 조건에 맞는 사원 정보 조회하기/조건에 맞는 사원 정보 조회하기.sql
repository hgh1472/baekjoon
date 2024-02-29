-- 코드를 작성해주세요
select SUM(SCORE) as SCORE, e.EMP_NO, e.EMP_NAME, e.POSITION, e.EMAIL from HR_EMPLOYEES as e
join HR_GRADE as g on e.EMP_NO = g.EMP_NO
group by g.EMP_NO
order by SCORE DESC
limit 1
