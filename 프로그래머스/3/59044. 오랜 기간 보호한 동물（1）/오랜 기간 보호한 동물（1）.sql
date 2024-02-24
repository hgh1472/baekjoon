-- 코드를 입력하세요
SELECT ins.NAME, ins.DATETIME  from ANIMAL_INS as ins
left join ANIMAL_OUTS as outs on ins.ANIMAL_ID = outs.ANIMAL_ID
where outs.ANIMAL_ID is null
order by ins.DATETIME
limit 3