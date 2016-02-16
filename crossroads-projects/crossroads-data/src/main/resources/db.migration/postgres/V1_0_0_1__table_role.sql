CREATE TABLE role
(
  id bigserial NOT NULL,
  created_date timestamp without time zone NOT NULL,
  created_by bigint DEFAULT NULL,
  last_modified_date timestamp without time zone NOT NULL,
  last_modified_by bigint DEFAULT NULL,
  status character varying(50) NOt NULL,
  version bigint NOT NULL,
  description character varying(255) DEFAULT NULL,
  type character varying(50) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

insert into role (status, version,created_date, last_modified_date,type,description) values ('ACTIVE',0,now(),now(),'SUPER_USER',null);