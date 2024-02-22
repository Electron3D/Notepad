--liquibase formatted sql

--changeset electron3d:1
create table if not exists comments (
    id bigserial not null,
    parent_comment_id bigint,
    user_id bigint,
    text varchar(255),
    primary key (id)
);
create table if not exists notes (
    id bigserial not null,
    user_id bigint,
    access_modifier varchar(255) check (access_modifier in ('PUBLIC','PRIVATE')),
    tag varchar(255) check (tag in ('IMPORTANT','ARCHIVE','DEPRECATED','REGULAR')),
    text varchar(255),
    primary key (id)
);
create table if not exists note_check_list (
    value boolean,
    note_id bigint not null,
    key varchar(255) not null,
    primary key (note_id, key)
);
create table if not exists notes_comments (
    comments_id bigint not null unique,
    note_id bigint not null
);
create table if not exists "users" (
    id bigserial not null,
    nickname varchar(255),
    primary key (id)
);

alter table if exists comments
    add constraint FKhvh0e2ybgg16bpu229a5teje7 foreign key (parent_comment_id) references comments;
alter table if exists comments
    add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references "users";
alter table if exists notes
    add constraint FKmoddtnuw3yy6ct34xnw6u0boh foreign key (user_id) references "users";
alter table if exists note_check_list
    add constraint FKdu7mvuxq2uyafp0uino428m8q foreign key (note_id) references notes;
alter table if exists notes_comments
    add constraint FKhavu1hydkyr2t5qeo4gq3e4cw foreign key (comments_id) references comments;
alter table if exists notes_comments
    add constraint FKhgi8xokypg3hnl46yfw6jx5q2 foreign key (note_id) references notes;
