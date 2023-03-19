create table computations (
  computationId bigint not null,
  starttime timestamp without time zone not null,
  endtime timestamp without time zone not null,
  "year" int,
  chunk int,
  CONSTRAINT pk_computations primary key (computationId)
);
