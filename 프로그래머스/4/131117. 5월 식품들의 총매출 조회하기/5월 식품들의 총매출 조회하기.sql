-- 코드를 입력하세요
select a.PRODUCT_ID, a.PRODUCT_NAME, sum(b.AMOUNT) * a.PRICE as TOTAL_SALES from FOOD_PRODUCT as a
join FOOD_ORDER as b on a.PRODUCT_ID = b.PRODUCT_ID
where b.PRODUCE_DATE like '2022-05%'
group by a.PRODUCT_ID
order by TOTAL_SALES desc, a.PRODUCT_ID