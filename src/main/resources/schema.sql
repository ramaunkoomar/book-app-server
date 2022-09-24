CREATE SCHEMA IF NOT EXISTS `book-db`;

CREATE TABLE IF NOT EXISTS `book`(
    `isbn` VARCHAR(45) NOT NULL,
    `title` VARCHAR(45) NOT NULL,
    `genre` VARCHAR(45),
    `summary` TEXT,
    PRIMARY KEY(`isbn`)
)