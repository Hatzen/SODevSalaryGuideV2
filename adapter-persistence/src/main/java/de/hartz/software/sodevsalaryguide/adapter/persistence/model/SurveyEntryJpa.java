package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import de.hartz.software.sodevsalaryguide.core.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "surveyentry")
@NoArgsConstructor
public class SurveyEntryJpa {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double salary;

    private Integer yearOfSurvey;

    // https://www.baeldung.com/jpa-persisting-enums-in-jpa
    @Enumerated(EnumType.STRING)
    private Currency currency;

    // https://stackoverflow.com/questions/25172083/creating-converters-based-on-multiple-attributes-in-jpa-2-1
    private Integer expirienceInYearsMin;
    private Integer expirienceInYearsMax;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(
            mappedBy = "surveyEntryJpa",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<AbilityJpa> abilities;

    private Integer age;

    private Integer companySizeMin;
    private Integer companySizeMax;

    private String highestDegree;

    private String country;
}
