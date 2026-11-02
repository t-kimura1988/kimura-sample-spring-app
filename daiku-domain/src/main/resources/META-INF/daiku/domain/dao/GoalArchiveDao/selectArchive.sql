select
    ga.id,
    ga.archives_create_date,
    ga.publish,
    ga.thoughts,
    ga.goal_id,
    g.create_date as goal_create_date,
    g.title,
    g.purpose,
    g.aim,
    g.due_date,
    a.family_name,
    a.given_name,
    a.nick_name,
    a.user_image,
    case when ga.publish='1' and g.account_id <> /* param.accountId */0 then 0 else (select count(1) from t_processes pc where pc.goal_id=g.id) end process_count,
    g.account_id as goal_create_account_id
from t_goal_archives ga
         inner join t_goals g on g.id = ga.goal_id
         inner join t_accounts a on g.account_id = a.id and a.del_flg='0'
where
    (g.account_id = /* param.accountId */0 or g.account_id <> /* param.accountId */0 and ga.publish='1' or ga.publish='2')
and
    ga.updating_flg = '0'
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