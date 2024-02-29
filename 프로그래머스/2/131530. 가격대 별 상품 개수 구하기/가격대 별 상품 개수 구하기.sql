select PRICE_GROUP, COUNT(*) as PRODUCTS from (select truncate(PRICE, -4) as PRICE_GROUP from PRODUCT as a) as b
group by PRICE_GROUP
order by PRICE_GROUP