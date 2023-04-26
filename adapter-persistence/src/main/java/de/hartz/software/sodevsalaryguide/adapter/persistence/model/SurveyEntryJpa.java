package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import de.hartz.software.sodevsalaryguide.core.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "surveyentry")
@NoArgsConstructor
public class SurveyEntryJpa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double salary;

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
