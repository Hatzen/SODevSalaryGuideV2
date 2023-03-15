
create table computations (
  computationId bigint not null,
  starttime timestamp without time zone not null,
  endtime timestamp without time zone not null,
  year varchar(8),
  chunk int,
  primary key (computationId)
);