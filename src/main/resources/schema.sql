DROP TABLE IF EXISTS TB_BOOK;

CREATE TABLE TB_BOOK (
                         `ID` NUMBER AUTO_INCREMENT,
                         `NAME` VARCHAR2(20) NOT NULL,
                         `VALUE` NUMBER,
                         PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS TB_AUTHOR;

CREATE TABLE TB_AUTHOR (
                         `ID` NUMBER AUTO_INCREMENT,
                         `NAME` VARCHAR2(20) NOT NULL,
                         `AGE` NUMBER,
                         `BOOK` NUMBER,
                         PRIMARY KEY (ID),
                         FOREIGN KEY (BOOK) REFERENCES TB_BOOK(ID)
);

DROP TABLE IF EXISTS TB_TITLE;

CREATE TABLE TB_TITLE (
                         `ID` NUMBER AUTO_INCREMENT,
                         `NAME` VARCHAR2(20) NOT NULL,
                         `AUTHOR` NUMBER,
                         PRIMARY KEY (ID),
                         FOREIGN KEY (AUTHOR) REFERENCES TB_AUTHOR(ID)
);