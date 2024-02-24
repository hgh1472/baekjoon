# -- 코드를 입력하세요
SELECT p.PRODUCT_CODE, p.PRICE * s.AMOUNT as SALES from PRODUCT as p
join (select PRODUCT_ID, SUM(SALES_AMOUNT) as AMOUNT from OFFLINE_SALE
group by PRODUCT_ID) as s on p.PRODUCT_ID = s.PRODUCT_ID
order by SALES desc, p.PRODUCT_CODE

