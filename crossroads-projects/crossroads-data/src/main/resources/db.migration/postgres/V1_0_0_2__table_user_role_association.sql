CREATE TABLE user_role_association
(
  id bigserial NOT NULL,
  created_date timestamp without time zone NOT NULL,
  created_by bigint DEFAULT NULL,
  last_modified_date timestamp without time zone NOT NULL,
  last_modified_by bigint DEFAULT NULL,
  status character varying(50) NOT NULL,
  version bigint NOT NULL,
  cm_user bigint NOT NULL REFERENCES cr_user(id),
  role bigint NOT NULL REFERENCES role(id),
  CONSTRAINT user_role_association_pkey PRIMARY KEY (id)
);