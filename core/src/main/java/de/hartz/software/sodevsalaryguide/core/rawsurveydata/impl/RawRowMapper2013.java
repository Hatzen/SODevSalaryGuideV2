package de.hartz.software.sodevsalaryguide.core.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.core.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.model.raw.ColumnList;
import java.util.Optional;

public class RawRowMapper2013 extends AbstractRawRowMapper {
  @Override
  public Integer MAPPER_FOR_YEAR() {
    return 2013;
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
    return new ColumnList(
        "Which of the following languages or technologies have you used significantly in the past year?",
        57,
        70);
  }

  public final String getDegreeColumnName() {
    return AbstractRawRowMapper.COLUMN_DONT_EXIST;
  }

  public final String getCompanyColumnName() {
    return "How many people work for your company";
  }

  public final String getCountryColumnName() {
    return "What Country or Region do you live in?";
  }
}
