package de.hartz.software.sodevsalaryguide.model.raw;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Data
public class HeaderMeta<T> {
    private final String name;
    private final Optional<Class<T>> type;
}
