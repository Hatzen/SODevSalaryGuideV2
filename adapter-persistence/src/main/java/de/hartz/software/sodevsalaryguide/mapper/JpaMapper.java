package de.hartz.software.sodevsalaryguide.mapper;

import de.hartz.software.sodevsalaryguide.model.AbilityJpa;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.SurveyEntryJpa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// https://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface JpaMapper {

    JpaMapper INSTANCE = Mappers.getMapper( JpaMapper.class );

    @InheritInverseConfiguration
    @Mapping(target = "convertedSalary", ignore = true)
    // @Mapping(target = "expirienceInYears", ignore = true)
    // @Mapping(target = "companySize", ignore = true)
    // @Mapping(target = "abilities", source = "abilities.ability")
    SurveyEntry surveyEntryJpaToDomain(SurveyEntryJpa surveyEntryJpa);

    @Mapping(target = "expirienceInYearsMin", source = "expirienceInYears.min")
    @Mapping(target = "expirienceInYearsMax", source = "expirienceInYears.max")
    @Mapping(target = "companySizeMin", source = "companySize.min")
    @Mapping(target = "companySizeMax", source = "companySize.max")
    @Mapping(target = "id", ignore = true)
    SurveyEntryJpa surveyEntryDomainToJpa(SurveyEntry surveyEntry);


    default Set<String> map(Set<AbilityJpa> value) {
        if (value == null) {
            return new HashSet<>();
        }
        return value.stream().map(AbilityJpa::getAbility).collect(Collectors.toSet());
    }

    default Set<AbilityJpa> mapToJpa(Set<String> value) {
        if (value == null) {
            return new HashSet<>();
        }
        return value.stream()
                .map(ability -> AbilityJpa.builder().ability(ability).build())
                .collect(Collectors.toSet());
    }

    @ObjectFactory
    default <T> Set<T> createCarDto() {
        return new HashSet<>();
    }
}