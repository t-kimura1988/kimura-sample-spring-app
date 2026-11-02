select
    m.id,
    m.maki_title,
    m.maki_key,
    m.maki_desc,
    m.account_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img
from
    t_makis m
        inner join
    t_accounts a on a.id = m.account_id
where
/*%if param.makiId != null */
    and
    m.id =  /* param.makiId */0
/*%end*/
