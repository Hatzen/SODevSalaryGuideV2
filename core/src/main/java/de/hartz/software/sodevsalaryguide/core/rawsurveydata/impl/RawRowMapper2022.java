package de.hartz.software.sodevsalaryguide.core.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.core.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.model.raw.ColumnList;
import java.util.Optional;

public class RawRowMapper2022 extends AbstractRawRowMapper {
  @Override
  public Integer MAPPER_FOR_YEAR() {
    return 2022;
  }

  public final String getSalaryColumnName() {
    return "ConvertedCompYearly";
  }

  public final String getCurrencyColumnName() {
    return "Currency";
  }

  public final String getGenderColumnName() {
    return "Gender";
  }

  public final String getYearsOfExperienceColumnName() {
    return "YearsCode";
  }

  public final Optional<String> getAbilitiesColumnName() {
    return Optional.of("LanguageHaveWorkedWith");
  }

  @Override
  public ColumnList getAbilitiesColumnList() {
    return null;
  }

  public final String getDegreeColumnName() {
    return "EdLevel";
  }

  public final String getCompanyColumnName() {
    return "OrgSize";
  }

  public final String getCountryColumnName() {
    return "Country";
  }
}
