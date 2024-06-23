package de.hartz.software.sodevsalaryguide.core.model;

import de.hartz.software.sodevsalaryguide.core.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class SurveyEntry {

    private Double salary;
    /**
     * Salary converted from origin currency to target currency.
     */
    @Builder.Default
    private Double convertedSalary = 0.0;

    private Integer yearOfSurvey;

    @Builder.Default
    private Currency currency = Currency.USD;

    // 2011-2014: How many years of IT/Programming experience do you have?
    // 2015: Years IT / Programming Experience   ; Acutally bad headers
    // 2016: experience_range and midpoint
    // 2017: YearsProgram
    // 2018: YearsCoding,YearsCodingProf
    // 2019: YearsCode,Age1stCode,YearsCodePro
    // 2020: YearsCode,YearsCodePro
    // 2021: YearsCode,YearsCodePro
    private Range expirienceInYears;
    // 2011 - 2013: ???
    // 2014: Gender
    // ...
    // 2020: Gender
    private Gender gender;
    // 2011: What type of project are you developing? => #30 - #43
    // 2012: What type of project are you developing? #22
    //    // Which languages are you proficient in? => #23 - #37
    // 2013: Which of the following languages or technologies have you used significantly in the past
    // year? => 57 - 70
    // 2014: Which of the following languages or technologies have you used significantly in the past
    // year? => 43 - 54
    // 2015: ??
    // 2016: tech_do => With ; seperated
    // 2017: HaveWorkedLanguage => ;
    // 2018 - 2020: LanguageWorkedWith => ;
    // 2021: LanguageHaveWorkedWith => ;
    private Set<String> abilities; // Abilities

    private Integer age;

    // 2011: Which best describes the size of your company?
    // 2012: Which best describes the size of your company?
    // 2013: How many people work for your company
    // 2014: How many developers are employed at your company
    // 2015: ???
    // 2016: company_size_range
    // 2017: CompanySize
    // 2018: CompanySize
    // 2019: OrgSize
    // 2020: OrgSize
    // 2021: OrgSize
    private Range companySize;

    // 2011: ??
    // ??
    // TODO: Possible?
    // 2015: 101
    // 2016: education
    // 2017: FormalEducation
    // 2018: FormalEducation
    // 2019: EdLevel
    // 2020: EdLevel
    // 2021: EdLevel
    private String highestDegree;

    // 2011: What Country or Region do you live in?
    // 2012: What Country or Region do you live in?
    // 2013: What Country or Region do you live in?
    // 2014: What Country do you live in?
    // 2015: 0 / 1
    // 2016: country
    // 2017: Country
    // 2018: Country
    // 2019: Country
    // 2020: Country
    // 2021: Country
    private String country;

    public boolean isValid() {
        return this.convertedSalary > 0;
    }
}
