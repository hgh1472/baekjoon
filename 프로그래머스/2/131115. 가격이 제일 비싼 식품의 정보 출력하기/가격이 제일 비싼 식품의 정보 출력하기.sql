-- 코드를 입력하세요
select * from FOOD_PRODUCT
where PRICE = (SELECT MAX(PRICE) from FOOD_PRODUCT)
