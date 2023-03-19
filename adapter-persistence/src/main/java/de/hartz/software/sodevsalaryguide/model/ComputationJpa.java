package de.hartz.software.sodevsalaryguide.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "computations")
@NoArgsConstructor
public class ComputationJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(nullable = false)
    private Long computationid;

    private LocalDateTime starttime;
    private LocalDateTime endtime;

    private Integer year;
    private Integer chunk;

}