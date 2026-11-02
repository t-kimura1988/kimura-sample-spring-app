select
    sc.id,
    sc.idea_id,
    sc.story_id,
    sc.chara_name,
    sc.chara_desc,
    sc.leader_flg
from
    t_story_characters sc

where
/*%if param.storyCharacterId != null */
  and
    sc.id =  /* param.storyCharacterId */0
/*%end*/
/*%if param.ideaId != null */
  and
    sc.idea_id =  /* param.ideaId */0
/*%end*/
/*%if param.storyId != null */
  and
    sc.story_id =  /* param.storyId */0
/*%end*/
/*%if param.leaderFlg != null */
  and
    sc.leader_flg =  /* param.leaderFlg */0
/*%end*/