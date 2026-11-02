select
    ga.id,
    ga.archives_create_date,
    ga.publish,
    ga.thoughts,
    ga.goal_id,
    ga.updating_flg,
    g.create_date as goal_create_date,
    g.title,
    g.purpose,
    g.aim,
    g.due_date,
    a.family_name,
    a.given_name,
    a.nick_name,
    a.user_image,
    (select count(1) from t_processes pc where pc.goal_id=g.id) process_count,
    g.account_id as goal_create_account_id
from t_goal_archives ga
         inner join t_goals g on g.id = ga.goal_id
         inner join t_accounts a on g.account_id = a.id
where
    g.account_id = /* param.accountId */0
/*%if param.archiveId != null */
and
    ga.id = /* param.archiveId */0
/*%end*/
/*%if param.archiveCreateDate != null */
and
    ga.archives_create_date = /* param.archiveCreateDate */0
/*%end*/
/*%if param.fromCreateDate != null && param.toCreateDate != null */
  and
    ga.archives_create_date between /* param.fromCreateDate */'2022-01-01' and /* param.toCreateDate */'2022-01-01'
/*%end*/
/*%if param.page != 0 */
    limit /* param.page */10
/*%end*/