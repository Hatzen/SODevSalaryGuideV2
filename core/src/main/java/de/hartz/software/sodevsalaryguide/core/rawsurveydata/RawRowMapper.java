package de.hartz.software.sodevsalaryguide.core.rawsurveydata;

import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.impl.*;
import lombok.val;

public class RawRowMapper {
  static final SurveyEntry INVALID_ENTRY = new SurveyEntry();
  private final AbstractRawRowMapper MAPPER_2011 = new RawRowMapper2011();
  private final AbstractRawRowMapper MAPPER_2012 = new RawRowMapper2012();
  private final AbstractRawRowMapper MAPPER_2013 = new RawRowMapper2013();
  private final AbstractRawRowMapper MAPPER_2014 = new RawRowMapper2014();
  private final AbstractRawRowMapper MAPPER_2015 = new RawRowMapper2015();
  private final AbstractRawRowMapper MAPPER_2016 = new RawRowMapper2016();
  private final AbstractRawRowMapper MAPPER_2017 = new RawRowMapper2017();
  private final AbstractRawRowMapper MAPPER_2018 = new RawRowMapper2018();
  private final AbstractRawRowMapper MAPPER_2019 = new RawRowMapper2019();
  private final AbstractRawRowMapper MAPPER_2021 = new RawRowMapper2021();
  private final AbstractRawRowMapper MAPPER_2022 = new RawRowMapper2022();

  public RawRowMapper() {
    RawRowMapper.INVALID_ENTRY.setSalary(-1.);
  }

  public SurveyEntry map(RawRow row) {
    AbstractRawRowMapper mapper;
    val year = row.getRawDataSetName().getYear();
    switch (year) {
      case 2011 -> mapper = this.MAPPER_2011;
      case 2012 -> mapper = this.MAPPER_2012;
      case 2013 -> mapper = this.MAPPER_2013;
      case 2014 -> mapper = this.MAPPER_2014;
      case 2015 -> mapper = this.MAPPER_2015;
      case 2016 -> mapper = this.MAPPER_2016;
      case 2017 -> // TODO: 2017 has "Currency" 2015 not..
      mapper = this.MAPPER_2017;
      case 2018 -> mapper = this.MAPPER_2018;
      case 2019, 2020 -> mapper = this.MAPPER_2019;
      case 2021 -> mapper = this.MAPPER_2021;
      case 2022 -> mapper = this.MAPPER_2022;
      default -> {
        // TODO: This can not happen?
        return RawRowMapper.INVALID_ENTRY;
      }
    }
    return mapper.map(row, year);
  }

  public boolean isValidEntry(SurveyEntry surveyEntry) {
    return surveyEntry != RawRowMapper.INVALID_ENTRY;
  }
}
