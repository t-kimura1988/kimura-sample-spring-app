delete from t_goal_favorites
where
/*%if entity.goalId != null */
    goal_id = /* entity.goalId */0
/*%end*/
/*%if entity.accountId != null */
and
    account_id = /* entity.accountId */0
/*%end*/