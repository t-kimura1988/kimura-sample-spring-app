select
    /*%expand*/*
from
    t_accounts
where
/*%if param.id != null */
  and
    id = /* param.id */0
/*%end*/
/*%if param.uid != null */
  and
    uid = /* param.uid */'0'
/*%end*/
/*%if param.delFlg != null */
  and
    del_flg = /* param.delFlg */'0'
/*%end*/
/*%if param.updatedAt != null */
  and
    updated_at < /* param.updatedAt */'2022-09-01'
/*%end*/