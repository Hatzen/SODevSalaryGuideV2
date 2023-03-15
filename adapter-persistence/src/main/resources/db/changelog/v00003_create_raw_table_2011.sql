create table raw_survey_entries_2011 (
  id bigint not null,
  computationId bigint not null references computations (id),
  column1 text not null,
  column2 text not null,
  column3 text not null,
  primary key (id)
);