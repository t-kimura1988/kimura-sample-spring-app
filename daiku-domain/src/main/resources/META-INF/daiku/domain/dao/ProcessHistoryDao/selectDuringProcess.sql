select
    concat(g.id,'-',p.id,'-',ph.id) as id,
    g.id as goal_id,
    g.create_date as goal_create_date,
    p.id as process_id,
    ph.id as process_history_id,
    a.family_name as created_account_family_name,
    a.given_name as created_account_given_name,
    a.user_image as created_account_img,
    g.title as goal_title,
    p.title as process_title,
    p.body as process_body,
    ph.process_start_date,
    ph.process_end_date,
    ga.id as archive_id,
    ga.archives_create_date,
    ga.updating_flg
from t_goals g
    inner join t_processes p on g.id = p.goal_id and g.account_id = /* param.accountId */0 and p.account_id = /* param.accountId */0
    inner join t_processes_history ph on p.id = ph.process_id
        and
            /* param.processHistoryDate */'2022-10-27' between ph.process_start_date and ph.process_end_date
        and
            ph.id = (
              select max(max_process_history.id)
              from t_processes_history max_process_history where max_process_history.process_id = ph.process_id)
    inner join t_accounts a on a.id = g.account_id and a.id = /* param.accountId */0
    left join
     t_goal_archives ga on g.id = ga.goal_id