package de.hartz.software.sodevsalaryguide.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "raw_survey_entries_2011")
@NoArgsConstructor
public class RawRowJpa {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String column1;
  private String column2;
  private String column3;
}
