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
    mgr.id as maki_relation_id
from
    t_goals g
    inner join
        t_accounts a on a.id = g.account_id and a.id = /* param.accountId */0
    left join
        t_goal_favorites f on g.id = f.goal_id and f.account_id = a.id
    left join
        t_goal_archives ga on g.id = ga.goal_id
    left join
        t_maki_goal_relation mgr on g.id = mgr.goal_id