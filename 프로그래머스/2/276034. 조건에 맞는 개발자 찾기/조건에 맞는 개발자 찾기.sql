-- 코드를 작성해주세요
select ID, EMAIL, FIRST_NAME, LAST_NAME from DEVELOPERS
where exists (select * from SKILLCODES
             where CODE & SKILL_CODE = CODE and (NAME = 'Python' or NAME = 'C#'))
order by ID asc