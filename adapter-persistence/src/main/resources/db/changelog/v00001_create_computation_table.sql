create table computations (
  computationId serial not null,
  starttime timestamp without time zone not null,
  endtime timestamp without time zone null,
  "year" int,
  chunk int,
  CONSTRAINT pk_computations primary key (computationId)
);
