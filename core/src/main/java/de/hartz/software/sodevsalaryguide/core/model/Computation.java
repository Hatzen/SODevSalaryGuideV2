package de.hartz.software.sodevsalaryguide.core.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Computation {
  private Long computationid;

  private LocalDateTime starttime;
  private LocalDateTime endtime;

  private Integer year;
  private Integer chunk;
}
