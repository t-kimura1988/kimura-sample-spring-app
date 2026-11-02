insert into t_accounts (id, family_name, given_name, uid, email, del_flg)
values (nextval('t_accounts_id_seq'), 'test', 'aaaaaa', 'testuid_111111', 'test@aaa.aaa', '0');
insert into t_accounts (id, family_name, given_name, uid, email, del_flg)
values (nextval('t_accounts_id_seq'), 'test', '当アプリの削除', 'testuid_111112', 'test@aaa.aaa', '1');
insert into t_accounts (id, family_name, given_name, uid, email, del_flg)
values (nextval('t_accounts_id_seq'), 'test', 'firebase削除連携済み', 'testuid_111113', 'test@aaa.aaa', '2');

insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_1', 'test', 'test', null, null, current_date , current_date , null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_2', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_3', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_4', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_5', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_6', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_7', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_8', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_9', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_10', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_11', 'test', 'test', null, null, current_date, current_date, null);


insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test1', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test2', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test3', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test4', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test5', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test6', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test7', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test8', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test9', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test10', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test11', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test12', '0', '0', null, current_date, current_date, null);

-- ID:1
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:2
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:3
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:4
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:5
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:6
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:7
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:8
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:9
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:10
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:11
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:12
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:13
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title', 'process body', null, current_date , current_date , null);
-- ID:14
insert into t_processes (id, goal_id, account_id, goal_create_date, title, body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), 'process title14', 'process body', null, current_date , current_date , null);


insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 1, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 2, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 3, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 4, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 5, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 6, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 7, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 8, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 9, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 10, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 11, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 12, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 13, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 14, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 14, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 14, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
insert into t_processes_history (id, process_id, account_id, goal_create_date, before_priority, priority, before_process_status, process_status, process_start_date, before_process_start_date, process_end_date, before_process_end_date, comment, before_title, before_body, created_by, created_at, updated_at, updated_by)
values (nextval('t_processes_history_id_seq'), 14, 1, to_char(current_date , 'yyyy-MM-dd'), null, '0', null, '0', '2022-09-08', null, '2022-09-08', null, null, null, null, null, current_date , current_date , null);
