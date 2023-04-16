create table raw_survey_entries (
  id bigint not null auto_increment,
  computationId bigint not null UNIQUE,


  CONSTRAINT fk_raw_survey_entries_2011_computations FOREIGN KEY (computationId) references computations (computationId),
  CONSTRAINT pk_raw_survey_entries_2011 primary key (id)
);

--ALTER TABLE computations
--    ADD CONSTRAINT fk_computations_raw_survey_entries_2011 FOREIGN KEY (computationId) REFERENCES raw_survey_entries (computationId)  ON DELETE CASCADE;