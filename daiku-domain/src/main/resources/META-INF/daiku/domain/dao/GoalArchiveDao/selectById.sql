select
    /*%expand*/*
from t_goal_archives
where
      id = /* param.archiveId */0
and
      archives_create_date = /* param.archiveCreateDate */'2022/03/03'