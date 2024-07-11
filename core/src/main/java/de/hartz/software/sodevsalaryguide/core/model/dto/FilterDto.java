package de.hartz.software.sodevsalaryguide.core.model.dto;

import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;

import java.util.Set;

public record FilterDto(
        Set<Integer> selectedYears,
        Range expirienceInYears,
        Set<Gender> genders,
        Set<String> abilities,
        Range companySize,
        Set<String> countries,
        Set<String> degrees
) {

}    // selectedYears!: { [year: number]: boolean }
// expirienceInYears!: [min: number, max:number]
// genders!: Gender[]
// abilities!: string[]
// companySize: [min: number, max:number] = [1, 100000]
// countries: string[] = []
// degrees: string[] = []



/*
Real payload currently differs


{selectedYears: {2022: true}, companySize: [1, 100000], countries: [], degrees: [],…}
abilitiesFilterActive
:
false
companySize
:
[1, 100000]
companySizeFilterActive
:
false
countries
:
[]
countriesFilterActive
:
false
degreeFilterActive
:
false
degrees
:
[]
expirienceFilterActive
:
false
gendersFilterActive
:
false
selectedYears
:
{2022: true}
 */