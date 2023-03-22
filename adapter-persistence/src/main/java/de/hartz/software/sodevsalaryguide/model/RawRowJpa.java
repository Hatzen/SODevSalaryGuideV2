package de.hartz.software.sodevsalaryguide.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "raw_survey_entries")
@NoArgsConstructor
public class RawRowJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "computationid")
  private ComputationJpa computation;

  // TODO: implement properly https://www.baeldung.com/hibernate-persisting-maps
  /*@ElementCollection
  @MapKeyColumn(name = "name")
  @Column(name = "value")
  @CollectionTable(name = "example_attributes", joinColumns = @JoinColumn(name = "example_id"))
  private Map<String, String> attributes =
      new HashMap<String, String>(); // maps from attribute name to value
   */
}
