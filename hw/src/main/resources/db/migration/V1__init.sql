create table books (
    id         int primary key auto_increment,
    title      varchar(255) not null,
    author     varchar(255) not null,
    isbn       varchar(20) not null,
    unique     uniq_isbn (isbn)
);

create table users
(
    id       int primary key auto_increment,
    login    varchar(30) not null,
    password varchar(100) not null,
    unique   uniq_login (login)
);

create table permissions
(
    id         int primary key auto_increment,
    permission varchar(30) not null,
    unique     uniq_permission (permission)
);

create table user_to_permissions (
    user_id       int not null,
    permission_id int not null,
    constraint fk_user_to_permission_user foreign key (user_id) references users(id),
    constraint fk_user_to_permission_permission foreign key (permission_id) references permissions(id)
);

create table favourite_books (
    user_id int not null,
    book_id int not null,
    constraint fk_favourite_books_user foreign key (user_id) references users(id),
    constraint fk_favourite_books_book foreign key (book_id) references books(id)
);

insert into books (title, author, isbn) values
    ('Kobzar', 'Taras Shevchenko', '1252136126'),
    ('Zapovit', 'Taras Shevchenko', '125515125'),
    ('Hamlet', 'William Shakespeare', '251618124'),
    ('Sonnets', 'William Shakespeare', '8989235982113'),
    ('Romeo and Julie', 'William Shakespeare', '88988912312512'),
    ('Faust', 'Johann Wolfgang von Goethe', '90129521512'),
    ('Der Erlkönig', 'Johann Wolfgang von Goethe', '1157182481287'),
    ('Egmont', 'Johann Wolfgang von Goethe', '8712758172512'),
    ('Dune', 'Frank Herbert', '90980192512532'),
    ('Dune: The Butlerian Jihad', 'Brian Herbert', '6782735832195321'),
    ('God-Emperor of Dune', 'Frank Herbert', '09819872657812');

insert into users (login, password) values
    ('admin', '$2a$10$cz2QBrBQvDZHvy/67ysM0.BTe8QzXj9UXpUpn4hpHPYm6gA.8tLCu'),
    ('manager', '$2a$10$cz2QBrBQvDZHvy/67ysM0.BTe8QzXj9UXpUpn4hpHPYm6gA.8tLCu'),
    ('user', '$2a$10$cz2QBrBQvDZHvy/67ysM0.BTe8QzXj9UXpUpn4hpHPYm6gA.8tLCu');

insert into permissions (permission) values
    ('VIEW_FAVOURITES'),
    ('ADD_FAVOURITE'),
    ('REMOVE_FAVOURITE'),
    ('ADD_BOOK'),
    ('VIEW_BOOK');

insert into user_to_permissions (user_id, permission_id) values
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'ADD_BOOK')),
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'VIEW_BOOK')),

    ((select id from users where login = 'user'), (select id from permissions where permission = 'VIEW_FAVOURITES')),
    ((select id from users where login = 'user'), (select id from permissions where permission = 'ADD_FAVOURITE')),
    ((select id from users where login = 'user'), (select id from permissions where permission = 'REMOVE_FAVOURITE'));

insert into favourite_books (user_id, book_id) values
    ((select id from users where login = 'user'), (select id from books where title = 'Hamlet')),
    ((select id from users where login = 'user'), (select id from books where title = 'Dune')),
    ((select id from users where login = 'user'), (select id from books where title = 'Egmont')),
    ((select id from users where login = 'user'), (select id from books where title = 'Faust')),
    ((select id from users where login = 'user'), (select id from books where title = 'Zapovit'));