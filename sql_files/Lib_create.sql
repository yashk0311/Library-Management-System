
CREATE DATABASE IIITBLibrary;
USE IIITBLibrary;

CREATE TABLE student(
    stu_roll_no VARCHAR(150) NOT NULL,
    stu_name VARCHAR(150) NOT NULL,
    issued_book_id VARCHAR(150),
    CONSTRAINT pk_student PRIMARY KEY (stu_roll_no)
);

CREATE TABLE archivist(
    archivist_id VARCHAR(150) NOT NULL,
    archivist_name VARCHAR(150) NOT NULL,
    archivist_pass VARCHAR(150) NOT NULL,
    CONSTRAINT pk_archivist PRIMARY KEY (archivist_id)
);

CREATE TABLE book(
    book_id VARCHAR(150) NOT NULL,
    book_name VARCHAR(150) NOT NULL,
    book_author VARCHAR(150) NOT NULL,
    published_in INTEGER NOT NULL,
    issued_by VARCHAR(150),
    CONSTRAINT pk_book PRIMARY KEY (book_id)
);

CREATE TABLE lib_head(
    lib_head_id VARCHAR(150) NOT NULL,
    lib_head_name VARCHAR(150) NOT NULL,
    lib_head_pass VARCHAR(150) NOT NULL,
    CONSTRAINT pk_lib_head PRIMARY KEY (lib_head_id)
);

