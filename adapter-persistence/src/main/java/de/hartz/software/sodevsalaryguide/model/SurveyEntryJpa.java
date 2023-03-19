package de.hartz.software.sodevsalaryguide.model;

import de.hartz.software.sodevsalaryguide.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.model.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "surveyentry")
@NoArgsConstructor
public class SurveyEntryJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double salary;

    private Currency currency;

    // https://stackoverflow.com/questions/25172083/creating-converters-based-on-multiple-attributes-in-jpa-2-1
    private Integer expirienceInYearsMin;
    private Integer expirienceInYearsMax;

    private Gender gender;

    @OneToMany(mappedBy="ability", fetch = FetchType.EAGER)
    private Set<AbilityJpa> abilities;

    private Integer age;

    private Integer companySizeMin;
    private Integer companySizeMax;

    private String highestDegree;

    private String country;
}