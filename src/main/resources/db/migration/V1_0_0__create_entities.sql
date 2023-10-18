create sequence hibernate_sequence as bigint increment by 1 start with 1 owned by none;

create table chat
(
    user_id    bigint,
    chat_state int not null,
    constraint chat_pkey primary key (user_id)
);

create table entry_draft
(
    user_id bigint,
    type    varchar(1)     null,
    amount  numeric(15, 2) null,
    constraint entry_draft_pkey primary key (user_id),
    constraint entry_draft_chat_user_id_fk foreign key (user_id) references chat (user_id)
        on delete cascade on update cascade,
    constraint entry_amount_check check (amount > 0)
);

create table entry
(
    id      bigint,
    user_id bigint         not null,
    type    varchar(1)     not null,
    amount  numeric(15, 2) not null,
    account varchar(32)    not null,
    date    timestamp      not null,
    constraint entry_pkey primary key (id),
    constraint entry_chat_user_id_fk foreign key (user_id) references chat (user_id)
        on delete cascade on update cascade,
    constraint entry_amount_check check (amount > 0)
);

create index entry_user_id_idx on entry (user_id);
