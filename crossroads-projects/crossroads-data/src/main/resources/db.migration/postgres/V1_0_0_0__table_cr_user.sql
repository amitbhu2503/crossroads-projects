CREATE TABLE cm_user
(
  id bigserial  NOT NULL,
  created_date timestamp without time zone NOT NULL,
  created_by bigint DEFAULT NULL,
  last_modified_date timestamp without time zone NOT NULL,
  last_modified_by bigint DEFAULT NULL,
  status character varying(50) NOT NULL,
  version bigint NOT NULL,
  avatar_name character varying(255),
  avatar_url character varying(500),
  dob timestamp without time zone DEFAULT NULL,
  email character varying(255) NOT NULL,
  failed_login_attempt integer NOT NULL,
  first_name character varying(255) NOT NULL,
  gender character varying(50),
  last_name character varying(255) NOT NULL,
  last_password_update_date timestamp without time zone DEFAULT NULL,
  password character varying(500) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);
