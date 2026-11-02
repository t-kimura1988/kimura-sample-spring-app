select
    s.id,
    s.idea_id,
    s.account_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    s.title,
    s.body,
    i.body as idea_body
from t_stories s
inner join
    t_accounts a on a.id = s.account_id
/*%if param.accountId != null */
    and
        s.account_id =  /* param.accountId */0
/*%end*/
inner join
    t_ideas i on i.id = s.idea_id
/*%if param.ideaId != null */
    and
        s.idea_id =  /* param.ideaId */0
/*%end*/
where
/*%if param.storyId != null */
and
    s.id =  /* param.storyId */0
/*%end*/