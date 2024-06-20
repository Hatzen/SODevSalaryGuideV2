create table surveyEntry (
  id serial not null,
  computationId bigint,
  salary float,
  yearOfSurvey int,
  currency text,
  expirienceInYearsMin int,
  expirienceInYearsMax int,
  gender text,
  age int,
  companySizeMin int,
  companySizeMax int,
  highestDegree text,
  country text,
  abilityId bigint,
  CONSTRAINT fk_surveyEntry_computations FOREIGN KEY (computationId) references computations (computationId),
  CONSTRAINT pk_surveyEntry primary key (id)
);

create table ability (
  id serial not null,
  surveyEntryId bigint UNIQUE,
  ability text,
  CONSTRAINT fk_ability_surveyEntry FOREIGN KEY (surveyEntryId) references surveyEntry (id),
  primary key (id)
);

ALTER TABLE surveyEntry
    ADD CONSTRAINT fk_surveyEntry_ability FOREIGN KEY (abilityId) REFERENCES ability (id)  ON DELETE CASCADE;

--ALTER TABLE computations
--    ADD CONSTRAINT fk_computations_surveyEntry FOREIGN KEY (computationId) REFERENCES surveyEntry (computationId)  ON DELETE CASCADE;