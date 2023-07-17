ALTER TABLE student
    ADD CONSTRAINT fk_issued_book FOREIGN KEY (issued_book_id) REFERENCES book(book_id);

ALTER TABLE book
    ADD CONSTRAINT fk_issued_by FOREIGN KEY (issued_by) REFERENCES student(stu_roll_no);
