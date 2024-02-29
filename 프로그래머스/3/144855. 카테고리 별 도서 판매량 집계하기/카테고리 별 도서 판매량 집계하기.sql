SELECT b.CATEGORY, SUM(SALES) as TOTAL_SALES from BOOK_SALES as a
join BOOK as b on a.BOOK_ID = b.BOOK_ID
where a.SALES_DATE like '2022-01%'
group by b.CATEGORY
order by CATEGORY