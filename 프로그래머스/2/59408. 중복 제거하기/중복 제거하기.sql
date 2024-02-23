-- 코드를 입력하세요
SELECT count(*)
from (select * from ANIMAL_INS
      group by NAME
      having NAME is not null) as a
