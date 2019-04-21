create table if not exists banner
(
    id int(11) not null auto_increment,
    category_id int(11) not null,
    deleted tinyint(1) not null default 0,
    content text not null,
    name varchar(255) not null unique,
    price decimal(8, 2) not null,
    primary key (id)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci;
create table if not exists category
(
    id int(11) not null auto_increment,
    deleted tinyint(1) not null default 0,
    name varchar(255) not null unique,
    req_name varchar(255) not null unique,
    primary key (id)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci;
create table if not exists request
(
    id int(11) not null auto_increment,
    banner_id int(11) not null,
    ip_address varchar(255) not null,
    user_agent text not null,
    date datetime not null,
    primary key (id)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci;
