package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "raw_survey_entries")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawRowJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // , mappedBy = "computationid" // TODO: Not usable as the id column is not the join column.
  // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  // @JoinColumn(name = "computationid")
  // private ComputationJpa computation;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(
      name = "computationid",
      referencedColumnName = "computationid",
      // updatable = false,
      nullable = false
      // insertable = false
      )
  // @MapsId
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
