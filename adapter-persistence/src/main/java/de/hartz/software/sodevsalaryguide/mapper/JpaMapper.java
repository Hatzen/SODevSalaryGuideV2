package de.hartz.software.sodevsalaryguide.mapper;

import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.SurveyEntryJpa;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// https://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface JpaMapper {

    JpaMapper INSTANCE = Mappers.getMapper( JpaMapper.class );

    @InheritConfiguration
    SurveyEntry surveyEntryJpaToDomain(SurveyEntryJpa surveyEntryJpa);

    @Mapping(target = "expirienceInYearsMin", source = "expirienceInYears.min")
    @Mapping(target = "expirienceInYearsMax", source = "expirienceInYears.max")
    @Mapping(target = "companySizeMin", source = "companySize.min")
    @Mapping(target = "companySizeMax", source = "companySize.max")
    @Mapping(target = "id", ignore = true)
    SurveyEntryJpa surveyEntryDomainToJpa(SurveyEntry surveyEntry);
}