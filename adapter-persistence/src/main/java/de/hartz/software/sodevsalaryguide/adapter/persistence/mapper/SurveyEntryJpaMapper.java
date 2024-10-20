package de.hartz.software.sodevsalaryguide.adapter.persistence.mapper;

import de.hartz.software.sodevsalaryguide.adapter.persistence.model.AbilityJpa;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.SurveyEntryJpa;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// https://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SurveyEntryJpaMapper {

    SurveyEntryJpaMapper INSTANCE = Mappers.getMapper(SurveyEntryJpaMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "convertedSalary", ignore = true)
    SurveyEntry surveyEntryJpaToDomain(SurveyEntryJpa surveyEntryJpa);

    @Mapping(target = "expirienceInYearsMin", source = "expirienceInYears.min")
    @Mapping(target = "expirienceInYearsMax", source = "expirienceInYears.max")
    @Mapping(target = "companySizeMin", source = "companySize.min")
    @Mapping(target = "companySizeMax", source = "companySize.max")
    @Mapping(target = "id", ignore = true)
    SurveyEntryJpa surveyEntryDomainToJpa(SurveyEntry surveyEntry);

    // TODO: Is there an more idiomatic way to do this?
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


    // https://stackoverflow.com/a/74549364/8524651
    @AfterMapping
    default void after(final @MappingTarget SurveyEntryJpa target) {
        target.getAbilities().forEach(it -> it.setSurveyEntryJpa(target));
    }

    @ObjectFactory
    default <T> Set<T> createHashset() {
        return new HashSet<>();
    }

    // https://stackoverflow.com/a/46533757/8524651
    @AfterMapping
    default void after(final @MappingTarget SurveyEntry target, final Range source) {
        if (source.min() == null && source.max() == null) {
            if (source == target.getCompanySize()) {
                target.setCompanySize(null);
            } else {
                target.setExpirienceInYears(null);
            }
        }
    }

    // https://stackoverflow.com/a/46533757/8524651
    @AfterMapping
    default void after(final @MappingTarget SurveyEntry target) {
        if (target.getCompanySize().min() == null && target.getCompanySize().max() == null) {
            target.setCompanySize(null);
        }
        if (target.getExpirienceInYears().min() == null
                && target.getExpirienceInYears().max() == null) {
            target.setExpirienceInYears(null);
        }
    }
}
