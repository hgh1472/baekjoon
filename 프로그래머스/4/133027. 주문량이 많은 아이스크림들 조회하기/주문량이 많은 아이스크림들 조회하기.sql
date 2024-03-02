-- 코드를 입력하세요
SELECT a.FLAVOR from FIRST_HALF as a
join (select sum(TOTAL_ORDER) as JULY_ORDER, FLAVOR from JULY
     group by FLAVOR) as b on a.FLAVOR = b.FLAVOR
order by (TOTAL_ORDER + JULY_ORDER) desc
limit 3