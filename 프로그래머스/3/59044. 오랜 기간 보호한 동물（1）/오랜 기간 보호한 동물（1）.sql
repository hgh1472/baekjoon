-- 코드를 입력하세요
SELECT ins.NAME, ins.DATETIME  from ANIMAL_INS as ins
where ins.ANIMAL_ID not in (select ANIMAL_ID from ANIMAL_OUTS)
order by DATETIME
limit 3
