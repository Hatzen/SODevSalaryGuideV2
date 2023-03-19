create table raw_survey_entries_2011 (
  id bigint not null,
  computationId bigint not null UNIQUE,
  column1 text not null,
  column2 text not null,
  column3 text not null,
  CONSTRAINT fk_raw_survey_entries_2011_computations FOREIGN KEY (computationId) references computations (computationId),
  CONSTRAINT pk_raw_survey_entries_2011 primary key (id)
);

ALTER TABLE computations
    ADD CONSTRAINT fk_computations_raw_survey_entries_2011 FOREIGN KEY (computationId) REFERENCES raw_survey_entries_2011 (computationId)  ON DELETE CASCADE;