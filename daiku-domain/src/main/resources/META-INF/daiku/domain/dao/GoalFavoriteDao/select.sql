select
    gf.id,
    gf.goal_id,
    gf.account_id,
    gf.favorite_add_date,
    goal_created_account.family_name as goal_created_account_family_name,
    goal_created_account.given_name as goal_created_account_given_name,
    goal_created_account.nick_name as goal_created_account_nick_name,
    goal_created_account.user_image as goal_created_account_Img,
    g.title,
    g.purpose,
    g.due_date,
    g.create_date as goal_create_date,
    favorited_account.family_name as favorite_add_account_family_name,
    favorited_account.given_name as favorite_add_account_given_name,
    favorited_account.nick_name as favorite_add_account_nick_name,
    favorited_account.user_image as favorite_add_account_Img,
    ga.id as archive_id,
    ga.archives_create_date
from
    t_goal_favorites gf
inner join t_goals g on g.id = gf.goal_id
inner join t_accounts goal_created_account on goal_created_account.id = gf.account_id
inner join t_accounts favorited_account on favorited_account.id = gf.account_id
left  join t_goal_archives ga on g.id = ga.goal_id
where
/*%if param.goalId != null */
    gf.goal_id = /* param.goalId */0
/*%end*/
/*%if param.accountId != null */
and
    gf.account_id = /* param.accountId */0
/*%end*/
/*%if param.favoriteCreateDateFrom != null && param.favoriteCreateDateTo != null*/
and
    g.create_date between /* param.favoriteCreateDateFrom */'2022-01-01' and /* param.favoriteCreateDateTo */'2022-01-01'
/*%end*/
/*%if param.page != 0 */
    limit /* param.page */10
/*%end*/
