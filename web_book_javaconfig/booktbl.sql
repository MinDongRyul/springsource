select * from spring_board;

select bno, title, writer, regdate, updatedate 
from spring_board 
where bno > 0 
order by bno desc;