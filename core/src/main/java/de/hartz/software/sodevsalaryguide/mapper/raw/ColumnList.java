package de.hartz.software.sodevsalaryguide.mapper.raw;

import lombok.Data;

@Data
public record ColumnList(String initial, Integer from, Integer to) {
}
