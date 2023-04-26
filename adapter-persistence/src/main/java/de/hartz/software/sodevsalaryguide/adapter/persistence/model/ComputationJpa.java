package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "computations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputationJpa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @Column(nullable = false)
  private Long computationid;

  // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  // @JoinColumn(name = "computationid")
  // private List<RawRowJpa> rawRowJpas;

  private LocalDateTime starttime;
  private LocalDateTime endtime;

  private Integer year;
  private Integer chunk;
}
