-- 코드를 입력하세요
SELECT CAR_ID,
MAX(CASE when '2022-10-16' between START_DATE and END_DATE then '대여중'
else '대여 가능'
end) as 'AVAILABILTY'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as c
group by c.CAR_ID
order by c.CAR_ID DESC