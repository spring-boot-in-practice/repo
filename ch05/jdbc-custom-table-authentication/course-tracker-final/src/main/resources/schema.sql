create table courses (
  id          INT NOT NULL,
  name        VARCHAR(255),
  category    VARCHAR(255),
  rating      int NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (id)
);

create table ct_users(
    ct_user varchar(50) not null primary key,
    ct_password varchar(500) not null,
    ct_enabled boolean not null
);

create table ct_authorities (
    ct_user varchar(50) not null,
    ct_authority varchar(50) not null,
    constraint fk_authorities_users foreign key(ct_user) references ct_users(ct_user)
);

create unique index ix_auth_username on ct_authorities (ct_user,ct_authority);