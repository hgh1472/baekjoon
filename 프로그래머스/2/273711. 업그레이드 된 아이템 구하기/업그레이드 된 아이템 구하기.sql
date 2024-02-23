-- 코드를 작성해주세요
select i.ITEM_ID, i.ITEM_NAME, i.RARITY from ITEM_INFO as i
join ITEM_TREE as T on T.ITEM_ID = I.ITEM_ID
join ITEM_INFO as root on T.PARENT_ITEM_ID = root.ITEM_ID
where root.RARITY = 'RARE'
order by i.ITEM_ID DESC
