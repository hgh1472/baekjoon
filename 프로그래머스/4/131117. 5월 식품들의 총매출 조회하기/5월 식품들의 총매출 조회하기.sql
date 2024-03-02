-- 코드를 입력하세요
select a.PRODUCT_ID, a.PRODUCT_NAME, b.AMOUNT * a.PRICE as TOTAL_SALES from FOOD_PRODUCT as a
join (select PRODUCT_ID, sum(AMOUNT) as AMOUNT from FOOD_ORDER
where PRODUCE_DATE like '2022-05%'
group by PRODUCT_ID) as b on a.PRODUCT_ID = b.PRODUCT_ID
order by TOTAL_SALES desc, a.PRODUCT_ID
