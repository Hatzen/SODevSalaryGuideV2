package de.hartz.software.sodevsalaryguide.core.model.raw;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HeaderMeta<T> {
  @EqualsAndHashCode.Include private final String name;
  private final Optional<Class<T>> type;
}
