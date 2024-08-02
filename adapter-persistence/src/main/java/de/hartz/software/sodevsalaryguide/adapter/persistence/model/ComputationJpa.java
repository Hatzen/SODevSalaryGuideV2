package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "computations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputationJpa {
    @Id
    // Needed after spring boot3.x update and removing postgres version:
    // Caused by: jakarta.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.tool.schema.spi.SchemaManagementException: Schema-validation: wrong column type encountered in column [computationid] in table [computations]; found [serial (Types#INTEGER)], but expecting [bigint (Types#BIGINT)]
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computationid;

    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "computationid")
    // private List<RawRowJpa> rawRowJpas;

    private LocalDateTime starttime;
    private LocalDateTime endtime;

    private Integer year;
    private Integer chunk;
}
