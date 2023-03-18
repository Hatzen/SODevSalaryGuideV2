package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2011;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2012;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2013;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2014;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2015;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2016;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2017;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2018;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2019;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2021;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl.RawRowMapper2022;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.raw.RawRow;

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

    private Integer year;

    public RawRowMapper (Integer year) {
        this.year = year;
        RawRowMapper.INVALID_ENTRY.setSalary(-1.);
    }

    public SurveyEntry map(RawRow row) {
        AbstractRawRowMapper mapper;
        switch (this.year) {
            case 2011:
                mapper = this.MAPPER_2011;
                break;
            case 2012:
                mapper = this.MAPPER_2012;
                break;
            case 2013:
                mapper = this.MAPPER_2013;
                break;
            case 2014:
                mapper = this.MAPPER_2014;
                break;
            case 2015:
                mapper = this.MAPPER_2015;
                break;
            case 2016:
                mapper = this.MAPPER_2016;
                break;
            case 2017: // TODO: 2017 has "Currency" 2015 not..
                mapper = this.MAPPER_2017;
                break;
            case 2018:
                mapper = this.MAPPER_2018;
                break;
            case 2019:
            case 2020:
                mapper = this.MAPPER_2019;
                break;
            case 2021:
                mapper = this.MAPPER_2021;
                break;
            case 2022:
                mapper = this.MAPPER_2022;
                break;
            default:
                // TODO: This can not happen?
                return RawRowMapper.INVALID_ENTRY;
        }
        return mapper.map(row);
    }
}
