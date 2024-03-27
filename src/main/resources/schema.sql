use postdb;

CREATE TABLE IF NOT EXISTS post
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    text_content varchar(1024)         NOT NULL,
    created_at   DATETIME     NOT NULL,
    created_by   VARCHAR(255) NOT NULL,
    updated_at   DATETIME     DEFAULT NULL,
    updated_by   VARCHAR(255) DEFAULT NULL
);