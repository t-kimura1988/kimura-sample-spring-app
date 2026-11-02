select
    mgr.id,
    mgr.goal_id,
    mgr.goal_create_date,
    mgr.maki_id,
    g.account_id
from
    t_maki_goal_relation mgr
inner join
    t_goals g
        on mgr.goal_id = g.id and g.create_date = mgr.goal_create_date
inner join
    t_makis m
        on mgr.maki_id = m.id
where
/*%if param.makiId != 0L */
    mgr.maki_id =  /* param.makiId */0
/*%end*/
/*%if param.goalId != null */
and
    mgr.goal_id =  /* param.goalId */0
/*%end*/
/*%if param.goalCreateDate != null */
and
    mgr.goal_create_date =  /* param.goalCreateDate */'2022-10-01'
/*%end*/