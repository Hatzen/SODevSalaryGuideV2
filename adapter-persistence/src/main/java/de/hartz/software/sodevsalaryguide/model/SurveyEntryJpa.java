package de.hartz.software.sodevsalaryguide.model;

import de.hartz.software.sodevsalaryguide.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.model.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class SurveyEntryJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(nullable = false)
    private Long id;

    private Double salary;

    private Currency currency;

    // https://stackoverflow.com/questions/25172083/creating-converters-based-on-multiple-attributes-in-jpa-2-1
    private Integer expirienceInYearsMin;
    private Integer expirienceInYearsMax;

    private Gender gender;

    private Set<String> abilities;

    private Integer age;

    private Range companySizeMin;
    private Range companySizeMax;

    private String highestDegree;

    private String country;
}