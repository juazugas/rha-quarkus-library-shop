insert into book (id, title, publication_year, isbn, price) values
    (1, 'The Hitchhiker''s Guide to the Galaxy', 1979, '0-330-25864-8', 10.00),
    (2, 'Snow Crash', 1992, '0-593-59973-X', 10.00),
    (3, 'Digital Fortress', 1998, '0-312-18087-X', 10.00)
    ;

insert into book_authors (book_id, name) values
    (1, 'Douglas Adams'),
    (2, 'Neal Stephenson'),
    (3, 'Dan Brown')
    ;

alter sequence if exists book_seq restart with 10;