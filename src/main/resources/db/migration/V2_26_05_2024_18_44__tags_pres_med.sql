create table if not exists tags (
    id serial,
    tagname              varchar(50) not null unique,
    primary key (id)
    );

CREATE TABLE medicaments (
    id serial,
    med_name varchar(50) not null,
    description varchar(80) not null,
    country varchar(50) not null,
    producer varchar(50) not null,
    price numeric not null,
    primary key (id)
);

CREATE TABLE med_tags (
    med_id               int not null,
    tag_id               int not null,
    primary key (med_id, tag_id),
    foreign key (med_id) references medicaments (id),
    foreign key (tag_id) references tags (id)
);

insert into tags (tagname)
values
    ('tag_1'), ('tag_2'),('tag_3'),('tag_4');




