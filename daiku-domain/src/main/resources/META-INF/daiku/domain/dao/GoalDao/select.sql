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
    mgr.id as maki_relation_id,
    mgr.sort_num,
    mk.maki_key
from
    t_goals g
inner join
        t_accounts a on a.id = g.account_id
left join
        t_goal_favorites f on g.id = f.goal_id and f.account_id = a.id
left join
        t_goal_archives ga on g.id = ga.goal_id
left join
        t_maki_goal_relation mgr on mgr.goal_id = g.id
left join
        t_makis mk on mk.id = mgr.maki_id
where
/*%if param.olderThan */
    g.create_date < /* param.fromCreateDate */'2022-01-01'
/*%end*/
/*%if !param.olderThan && param.fromCreateDate != null && param.toCreateDate != null */
    and
    g.create_date between /* param.fromCreateDate */'2022-01-01' and /* param.toCreateDate */'2022-01-01'
/*%end*/
/*%if param.createDate != null */
    and
    g.create_date = /* param.createDate */'2022-01-01'
/*%end*/
/*%if param.goalId != null */
    and
    g.id =  /* param.goalId */0
/*%end*/
/*%if param.accountId != null */
    and
    g.account_id =  /* param.accountId */0
/*%end*/
order by g.due_date
/*%if param.page != 0 */
    limit /* param.page */10
/*%end*/
