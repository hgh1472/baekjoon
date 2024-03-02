-- 코드를 입력하세요
SELECT a.CAR_ID, a.CAR_TYPE, ROUND((a.DAILY_FEE * 30 * (100 - c.DISCOUNT_RATE) / 100)) as FEE from CAR_RENTAL_COMPANY_CAR as a
left join CAR_RENTAL_COMPANY_RENTAL_HISTORY as b on a.CAR_ID = b.CAR_ID
AND b.END_DATE >= '2022-11-01' AND b.START_DATE <= '2022-11-30'
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as c on a.CAR_TYPE = c.CAR_TYPE
where a.CAR_TYPE in ('세단', 'SUV') and c.DURATION_TYPE like '30일%' and b.CAR_ID is null
group by a.CAR_ID
having FEE between 500000 and 1999999
order by FEE desc, a.CAR_TYPE, a.CAR_ID desc


# SELECT C.CAR_ID AS CAR_ID
#      , C.CAR_TYPE AS CAR_TYPE
#      , FLOOR(C.DAILY_FEE * 30 * (1 - P.DISCOUNT_RATE/100)) AS FEE
# FROM CAR_RENTAL_COMPANY_CAR AS C
#     INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS P ON C.CAR_TYPE = P.CAR_TYPE
#                                                     AND P.DURATION_TYPE = '30일 이상'
#                                                     AND C.CAR_TYPE IN ('세단', 'SUV')
#     LEFT JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H ON C.CAR_ID = H.CAR_ID
#                                                     AND H.END_DATE >= '2022-11-01'
#                                                     AND H.START_DATE <= '2022-11-30'
# WHERE ROUND(C.DAILY_FEE * 30 * (1 - P.DISCOUNT_RATE/100)) BETWEEN 500000 AND 1999999
#   AND H.CAR_ID IS NULL
# ORDER BY FEE DESC
#        , CAR_TYPE
#        , CAR_ID DESC