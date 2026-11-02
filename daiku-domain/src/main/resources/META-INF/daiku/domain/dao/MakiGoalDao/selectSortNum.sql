select
    max(mgr.sort_num)
from
    t_maki_goal_relation mgr
where
    mgr.maki_id = /* makiId */0
group by mgr.maki_id