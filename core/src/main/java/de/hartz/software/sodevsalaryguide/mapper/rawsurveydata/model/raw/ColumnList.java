package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.model.raw;

import lombok.Data;

@Data
public record ColumnList(String initial, Integer from, Integer to) {
}
