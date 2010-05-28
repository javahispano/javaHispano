CREATE DATABASE javahispano WITH ENCODING = 'UTF8';

CREATE TABLE account
(
  id serial, 
  username character varying,
  password character varying,
  email character varying,
  openid character varying,
  gravatar character varying,
  linkedin character varying,
  first_name character varying,
  last_name character varying,
  last_login timestamp without time zone,
  last_modification timestamp without time zone,
  signup_date timestamp without time zone,
  deleted boolean,
  enabled boolean,
  account_non_expired boolean,
  account_non_locked boolean,
  credentials_non_expired boolean,
  CONSTRAINT account_id_pkey PRIMARY KEY (id)
);

CREATE TABLE role
(
  id serial,
  name character varying,
  description character varying,
  CONSTRAINT role_id_pkey PRIMARY KEY (id)
);

CREATE TABLE account_role
(
  account_id bigint,
  role_id integer,
  CONSTRAINT account_role_account_id_fkey FOREIGN KEY (account_id)
      REFERENCES account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "content"
(
  id serial,
  user_id integer,
  title character varying,
  slugged_title character varying,
  body text,
  highlighted boolean,
  highlight_order smallint,
  draft boolean,
  num_visits integer,
  num_comments integer,
  last_comment time without time zone,
  creation_date timestamp without time zone,
  modification_date timestamp without time zone,
  CONSTRAINT content_id_pkey PRIMARY KEY (id)
);

CREATE TABLE tag
(
  id serial,
  "name" character varying,
  times_used integer,
  CONSTRAINT tag_id_pkey PRIMARY KEY (id)
);

CREATE TABLE content_tag
(
  content_id bigint,
  tag_id integer,
  CONSTRAINT content_tag_content_id_fkey FOREIGN KEY (content_id)
      REFERENCES "content" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT content_tag_tag_id_fkey FOREIGN KEY (tag_id)
      REFERENCES tag (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "comment"
(
  id serial,
  content_id integer,
  user_id integer,
  body text,
  deleted boolean,
  creation_date timestamp without time zone,
  CONSTRAINT comment_id_pkey PRIMARY KEY (id),
  CONSTRAINT command_content_id_fkey FOREIGN KEY (content_id)
      REFERENCES "content" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
