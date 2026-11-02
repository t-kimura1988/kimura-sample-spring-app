select
    g.id,
    g.create_date,
    g.title,
    g.purpose,
    g.aim,
    g.due_date,
    g.account_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    f.id as favorite_id,
    ga.id as archive_id,
    ga.archives_create_date,
    ga.updating_flg,
    mg.id as maki_relation_id,
    mg.sort_num,
    m.maki_key
from
    t_maki_goal_relation mg
    inner join
        t_goals g on g.id = mg.goal_id
    inner join
        t_makis m on m.id = mg.maki_id
    inner join
        t_accounts a on a.id = g.account_id
    left join
        t_goal_favorites f on g.id = f.goal_id and f.account_id = a.id
    left join
        t_goal_archives ga on g.id = ga.goal_id
where
/*%if param.makiId != null */
    mg.maki_id = /* param.makiId */0
/*%end*/
/*%if param.accountId != null */
  and
    m.account_id =  /* param.accountId */0
/*%end*/
order by mg.sort_num