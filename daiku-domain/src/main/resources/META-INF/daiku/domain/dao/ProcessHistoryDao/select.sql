select
    ph.id,
    ph.process_id,
    ph.account_id,
    ph.goal_create_date,
    ph.before_priority,
    ph.priority,
    ph.before_process_status,
    ph.process_status,
    ph.before_process_start_date,
    ph.process_start_date,
    ph.before_process_end_date,
    ph.process_end_date,
    ph.comment,
    ph.before_title,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    p.goal_id
from
    t_processes_history ph
/*%if param.accountId != null */
        inner join t_accounts a on  a.id = ph.account_id and ph.account_id = /* param.accountId */0
        inner join t_processes p on p.id = ph.process_id
/*%end*/
where
/*%if param.processId != null */
    ph.process_id = /* param.processId */0
/*%end*/
/*%if param.goalCreateDate != null */
and
    ph.goal_create_date = /* param.goalCreateDate */0
/*%end*/
/*%if param.id != null */
and
    ph.id = /* param.id */0
/*%end*/
/*%if param.latestFlg != null */
and
    ph.id = (
        select max(max_process_history.id)
        from t_processes_history max_process_history where max_process_history.process_id = ph.process_id)
/*%end*/
order by ph.id

