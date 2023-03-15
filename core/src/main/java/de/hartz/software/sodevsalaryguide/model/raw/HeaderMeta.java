package de.hartz.software.sodevsalaryguide.model.raw;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HeaderMeta<T> {
    @EqualsAndHashCode.Include
    private final String name;
    private final Optional<Class<T>> type;
}
