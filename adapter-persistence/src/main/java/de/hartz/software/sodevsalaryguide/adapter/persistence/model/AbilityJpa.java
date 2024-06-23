package de.hartz.software.sodevsalaryguide.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "ability")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbilityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Exclude so no stackoverflow when query survey jpa.
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "surveyentryid")
    private SurveyEntryJpa surveyEntryJpa;

    private String ability;
}
