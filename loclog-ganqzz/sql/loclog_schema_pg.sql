DROP TABLE IF EXISTS loclogs;
DROP TABLE IF EXISTS users;

-- users テーブル
CREATE TABLE users (
    user_name varchar(30) PRIMARY KEY,
    password varchar(40) NOT NULL,
    api_key text,
    email varchar(256) UNIQUE,
    role smallint NOT NULL DEFAULT 0,
    created_at timestamp,
    updated_at timestamp
);

-- loclogs テーブル
CREATE TABLE loclogs (
    log_id serial PRIMARY KEY,
    user_name varchar(30) NOT NULL REFERENCES users ON UPDATE CASCADE ON DELETE CASCADE,
    tag text,
    address text,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    fix_time bigint NOT NULL,
    note text,
    image_uri text,
    image text,
    thumbnail text,
    open boolean NOT NULL DEFAULT FALSE,
    created_at timestamp,
    updated_at timestamp
);

