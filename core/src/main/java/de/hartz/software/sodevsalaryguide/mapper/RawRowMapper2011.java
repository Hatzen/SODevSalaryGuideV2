package de.hartz.software.sodevsalaryguide.mapper;

import de.hartz.software.sodevsalaryguide.mapper.raw.ColumnList;

public class RawRowMapper2011 extends AbstractRawRowMapper {
    @Override
    public Integer MAPPER_FOR_YEAR() {
        return 2011;
    }

    public final String SALARY_KEY() {
        return "Including bonus, what is your annual compensation in USD?";
    }

    public final String CURRENCY_KEY() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String GENDER_KEY() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String YEARS_OF_EXPIERIENCE() {
        return "How many years of IT/Programming experience do you have?";
    }

    // TODO: proper solution for handling columnlist vs keys..
    public final String ABILITIES_KEY() {
        return null;
    }

    @Override
    ColumnList ABILITIES_KEYs() {
        return new ColumnList("What type of project are you developing?",
                30,
                43);
    }

    public final String DEGREE() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String COMPANY_SIZE() {
        return "Which best describes the size of your company?";
    }

    public final String COUNTRY() {
        return "What Country or Region do you live in?";
    }

}
