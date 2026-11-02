select
/*%expand*/*
from
    t_goal_favorites
where
    goal_id = /* param.goalId */0
and
    account_id = /* param.accountId */0