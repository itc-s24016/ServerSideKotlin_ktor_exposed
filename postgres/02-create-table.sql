\c exposed_example;

CREATE TABLE member(
                       id serial NOT NULL ,
                       name character varying(32) NOT NULL ,
                       PRIMARY KEY (id)
);

ALTER TABLE member OWNER TO exposed;