CREATE SEQUENCE IF NOT EXISTS t_accounts_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_accounts_id_seq IS 'アカウントIDシーケンス';

CREATE SEQUENCE IF NOT EXISTS t_goals_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_goals_id_seq IS 'goals table id sequence';


CREATE SEQUENCE IF NOT EXISTS t_processes_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_processes_id_seq IS 'processes table id sequence';

CREATE SEQUENCE IF NOT EXISTS t_processes_history_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_processes_history_id_seq IS 'processes history table id sequence';

CREATE SEQUENCE IF NOT EXISTS t_goal_favorites_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_goal_favorites_id_seq IS 'goal favorites table id sequence';

CREATE SEQUENCE IF NOT EXISTS t_goal_archive_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_goal_archive_id_seq IS 'goal archive table id sequence';

CREATE SEQUENCE IF NOT EXISTS t_makis_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_makis_id_seq IS 'makis table id sequence';

CREATE SEQUENCE IF NOT EXISTS t_maki_goal_relation_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE t_maki_goal_relation_id_seq IS 'maki_goal_relation table id sequence';

CREATE TABLE IF NOT EXISTS t_accounts
(
    id          NUMERIC      NOT NULL CONSTRAINT t_account_pk PRIMARY KEY,
    uid         VARCHAR(50)  NOT NULL,
    family_name VARCHAR(100) NOT NULL,
    given_name  VARCHAR(100) NOT NULL,
    nick_name   VARCHAR(100),
    user_image  VARCHAR(255),
    profile_back_image     VARCHAR(100),
    email       VARCHAR(255) NOT NULL,
    tel         VARCHAR(20),
    post_num    VARCHAR(8),
    birth_day   date,
    del_flg     VARCHAR(1) default '0' NOT NULL,
    created_at  timestamp default CURRENT_TIMESTAMP,
    updated_at  timestamp default CURRENT_TIMESTAMP,
    updated_by  NUMERIC
    );

COMMENT ON COLUMN t_accounts.uid IS 'firebase.uid';

COMMENT ON COLUMN t_accounts.family_name IS '苗字';

COMMENT ON COLUMN t_accounts.given_name IS '名前';

COMMENT ON COLUMN t_accounts.nick_name IS 'ニックネーム';

COMMENT ON COLUMN t_accounts.user_image IS 'ユーザー画像';

COMMENT ON COLUMN t_accounts.email IS 'メールアドレス';

COMMENT ON COLUMN t_accounts.post_num IS '住所_郵便番号';

CREATE UNIQUE INDEX IF NOT EXISTS t_account_uid_uindex
    on t_accounts (uid);

CREATE TABLE IF NOT EXISTS t_goals
(
    id           NUMERIC NOT NULL,
    account_id   NUMERIC NOT NULL,
    create_date  DATE DEFAULT CURRENT_DATE,
    title        VARCHAR(255) NOT NULL,
    purpose      TEXT,
    aim          TEXT,
    due_date     DATE,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   NUMERIC,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by   NUMERIC
    )
;
CREATE UNIQUE INDEX IF NOT EXISTS ix_goals_1
    ON t_goals(id, create_date)
;

CREATE TABLE IF NOT EXISTS t_processes
(
    id                     NUMERIC NOT NULL,
    goal_id                NUMERIC NOT NULL,
    account_id             NUMERIC NOT NULL,
    goal_create_date       DATE,
    title                  VARCHAR(255) NOT NULL,
    body                   TEXT,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by             NUMERIC,
    updated_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by             NUMERIC
    )
;
CREATE UNIQUE INDEX IF NOT EXISTS ix_processes_1
    ON t_processes(id, goal_create_date)
;

CREATE TABLE IF NOT EXISTS t_processes_history
(
    id                     NUMERIC NOT NULL,
    goal_create_date       DATE,
    process_id             NUMERIC NOT NULL,
    account_id             NUMERIC NOT NULL,
    comment                TEXT,
    before_title           VARCHAR(255),
    before_body            TEXT,
    before_priority        VARCHAR(2),
    priority               VARCHAR(2),
    before_process_status  VARCHAR(2),
    process_status         VARCHAR(2),
    before_process_start_date     DATE,
    process_start_date     DATE,
    before_process_end_date       DATE,
    process_end_date       DATE,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by             NUMERIC,
    updated_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by             NUMERIC
    )
;
CREATE UNIQUE INDEX IF NOT EXISTS ix_process_history_1
    ON t_processes_history(id, goal_create_date)
;

CREATE TABLE IF NOT EXISTS t_goal_favorites
(
    id                     NUMERIC NOT NULL,
    favorite_add_date      DATE,
    goal_id                NUMERIC NOT NULL,
    account_id             NUMERIC NOT NULL,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by             NUMERIC,
    updated_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by             NUMERIC
);
CREATE UNIQUE INDEX IF NOT EXISTS ix_process_history_1
    ON t_goal_favorites(id, favorite_add_date)
;

CREATE TABLE IF NOT EXISTS t_goal_archives
(
    id                     NUMERIC NOT NULL,
    archives_create_date   DATE NOT NULL,
    goal_id                NUMERIC NOT NULL,
    publish                VARCHAR(2),
    updating_flg           VARCHAR(1),
    thoughts               TEXT,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by             NUMERIC,
    updated_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by             NUMERIC
    )
;
CREATE UNIQUE INDEX IF NOT EXISTS ix_goal_archives_1
    ON t_goal_archives(id, archives_create_date)
;

CREATE TABLE IF NOT EXISTS t_makis
(
    id                NUMERIC NOT NULL,
    account_id        NUMERIC NOT NULL,
    maki_title        VARCHAR(255) NOT NULL,
    maki_key          VARCHAR(100),
    maki_desc         TEXT,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by        NUMERIC,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by        NUMERIC
    )
;
ALTER TABLE t_makis ADD CONSTRAINT pk_makis PRIMARY KEY (id)
;

CREATE TABLE IF NOT EXISTS t_maki_goal_relation
(
    id                NUMERIC NOT NULL,
    maki_id           NUMERIC NOT NULL,
    goal_id           NUMERIC NOT NULL,
    sort_num          NUMERIC NOT NULL,
    goal_create_date  DATE DEFAULT CURRENT_DATE,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by        NUMERIC,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by        NUMERIC
)
;

CREATE UNIQUE INDEX IF NOT EXISTS ix_maki_goal_relation_1
    ON t_maki_goal_relation(id, maki_id, goal_id, goal_create_date)
;

