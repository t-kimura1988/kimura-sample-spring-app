select
    /*%expand*/*
from t_ideas
where
        id = /* param.ideaId */0
  and
        account_id = /* param.accountId */0