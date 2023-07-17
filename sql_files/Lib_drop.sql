ALTER TABLE student 
    DROP FOREIGN KEY fk_issued_book;

ALTER TABLE book 
    DROP FOREIGN KEY fk_issued_by;

DROP TABLE student;
DROP TABLE archivist;
DROP TABLE book;
DROP TABLE lib_head;

DROP DATABASE IIITBLibrary;


