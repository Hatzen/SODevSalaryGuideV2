package de.hartz.software.sodevsalaryguide.core.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.core.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.model.raw.ColumnList;
import java.util.Optional;

public class RawRowMapper2012 extends AbstractRawRowMapper {
  @Override
  public Integer MAPPER_FOR_YEAR() {
    return 2012;
  }

  public final String getSalaryColumnName() {
    return "Including bonus, what is your annual compensation in USD?"; // 2011 - 2014
  }

  public final String getCurrencyColumnName() {
    return AbstractRawRowMapper.COLUMN_DONT_EXIST;
  }

  public final String getGenderColumnName() {
    return AbstractRawRowMapper.COLUMN_DONT_EXIST; // 2011 - 2013
  }

  public final String getYearsOfExperienceColumnName() {
    return "How many years of IT/Programming experience do you have?";
  }

  public final Optional<String> getAbilitiesColumnName() {
    return Optional.empty();
  }

  @Override
  public ColumnList getAbilitiesColumnList() {
    return new ColumnList("What type of project are you developing?", 23, 37);
  }

  public final String getDegreeColumnName() {
    return AbstractRawRowMapper.COLUMN_DONT_EXIST;
  }

  public final String getCompanyColumnName() {
    return "Which best describes the size of your company?";
  }

  public final String getCountryColumnName() {
    return "What Country or Region do you live in?";
  }
}
