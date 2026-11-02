select
    i.id,
    i.body,
    a.id as account_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    s.id as story_id,
    s.title,
    s.body as story_body
from
    t_ideas i
inner join t_accounts a on a.id = i.account_id
left join t_stories s on i.id = s.idea_id
where
/*%if param.ideaId != null */
and
    i.id =  /* param.ideaId */0
/*%end*/
/*%if param.accountId != null */
and
    i.account_id =  /* param.accountId */0
/*%end*/
/*%if param.page != 0 */
    limit /* param.page */10
/*%end*/
