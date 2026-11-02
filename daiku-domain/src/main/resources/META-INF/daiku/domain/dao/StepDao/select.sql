select
    s.id,
    s.create_date,
    s.account_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    s.body
from t_steps s
         inner join
     t_accounts a on a.id = s.account_id
/*%if param.accountId != null */
         and
                     s.account_id =  /* param.accountId */0
/*%end*/
where
/*%if param.stepId != null */
  and
    s.id =  /* param.stepId */0
/*%end*/
/*%if param.createDate != null */
  and
    s.create_date =  /* param.createDate */0
/*%end*/