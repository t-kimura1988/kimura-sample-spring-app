select
    /*%expand*/*
from t_stories
where
        id = /* param.storyId */0
  and
        idea_id = /* param.ideaId */0