insert into categories(owner_id, catname_rus, catname_en)
values (null, 'Книги', 'Books'), (null, 'Журналы', 'Magazine'), (null, 'Газеты', 'Newspapers'),
(1, 'Научные', 'Science'), (3, 'Информационные', 'Information'), (2, 'Научные', 'Science'),
(1, 'Художественные', 'Picture'), (1, 'Детские', 'Children'),
(2, 'Публицистические', 'Public'), (3, 'Аналитические', 'Analytical');

insert into documents(doc_name, doc_author, doc_year, doc_source, doc_keyword, doc_dateinput, annotation, cat_id, typedoc_id, path_id)
values ('Android. Полное руководство по разработке приложений', 'АСТ', '2024', 'authent.pdf', 'java, Android', '2025-05-01', '', 4, 2, 1),
('Java', 'Tom Mathew', '1999', 'config.pdf', 'java, middle', '2025-05-01', 'middle', 5, 2, 1),
('Волшебник Изумрудного города', 'Александр Мелентьевич Волков', '2023', 'config.pdf', 'волшебник', '2025-05-01', 'сказка', 8, 2, 1),
('C#. Основы программирования', 'АСТ', '2024', 'c.pdf', 'C#', '2025-05-01', '', 6, 1, 1),
('СТЭК-В', 'АО НИИЭТ', '2025', 'authent.pdf', 'СТЭК', '2025-05-01', '', 6, 1, 1),
('Война и мир', 'Лев Толстой', '2005', 'authent.pdf', 'война, мир', '2025-05-01', '', 7, 2, 1);

insert into typedocuments(typedoc_name)
values ('журнал'), ('книга'), ('презентация');

insert into pathsavepdf(path_name)
values ('D:\pdf');

insert into users(username, password, role)
values ('user', '$2y$10$CPCRLRXmdgugBC23KTkfR.419L6J3PGRUZ1y/SLKPoduPQWBSqKQq', 'USER'),
('admin', '$2y$10$9cCTtCpy/CFnNISlwGo4bOjJrb5kkFAJpeNNgSceAuh6n8GvpyeHe', 'ADMIN')