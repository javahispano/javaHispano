CREATE DATABASE javahispano WITH ENCODING = 'UTF8';

CREATE TABLE "content"
(
  id bigserial NOT NULL,
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
  id serial NOT NULL,
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
