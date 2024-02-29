-- 코드를 작성해주세요
select d.DEPT_ID, d.DEPT_NAME_EN, round(avg(e.SAL)) as AVG_SAL from HR_DEPARTMENT as d
join HR_EMPLOYEES as e on e.DEPT_ID = d.DEPT_ID
group by d.DEPT_ID
order by AVG_SAL desc