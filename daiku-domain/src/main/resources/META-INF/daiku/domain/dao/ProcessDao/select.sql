select
    p.id,
    p.goal_id,
    p.account_id,
    p.goal_create_date,
    p.title,
    p.body,
    ps.before_priority,
    ps.priority,
    ps.before_process_status,
    ps.process_status,
    ps.process_start_date,
    ps.process_end_date,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img
from
    t_processes p
inner join t_processes_history ps on p.id = ps.process_id
      and ps.id = (select max(max_process_history.id) from t_processes_history max_process_history where max_process_history.process_id = p.id)
inner join t_accounts a on  a.id = p.account_id
where
/*%if param.goalId != null */
    p.goal_id = /* param.goalId */0
/*%end*/
/*%if param.id != null */
  and
    p.id = /* param.id */0
/*%end*/
/*%if param.createDate != null */
  and
    p.goal_create_date = /* param.createDate */'2022-01-01'
/*%end*/
order by p.id

