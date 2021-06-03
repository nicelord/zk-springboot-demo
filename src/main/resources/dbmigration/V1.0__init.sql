-- apply changes
create table users (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password                      varchar(255) not null,
  access                        varchar(255),
  constraint uq_users_username unique (username),
  constraint pk_users primary key (id)
);

