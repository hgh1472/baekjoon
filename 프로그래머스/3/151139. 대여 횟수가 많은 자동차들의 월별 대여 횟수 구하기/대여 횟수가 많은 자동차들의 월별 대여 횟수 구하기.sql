select MONTH(START_DATE) as MONTH, CAR_ID, COUNT(CAR_ID) as RECORDS from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where START_DATE between '2022-08-01' and '2022-10-31' and 
CAR_ID in (select CAR_ID from CAR_RENTAL_COMPANY_RENTAL_HISTORY
           where START_DATE between '2022-08-01' and '2022-10-31'
          group by CAR_ID
          having count(CAR_ID) >= 5)
group by extract(month from START_DATE), CAR_ID
order by MONTH, CAR_ID DESC
