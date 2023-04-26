package de.hartz.software.sodevsalaryguide.core.model.raw;

import java.util.HashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawRow extends HashMap<HeaderMeta<?>, String> {

  @Getter private final RawDataSetName rawDataSetName;

  public String get(String columName) {
    return getValueForColumnName(columName);
  }

  public String getValueForColumnName(String columName) {
    return super.get(columName);
  }

  // TODO: This will obviously not work deterministicly with Hash implementation
  public String getValueForColumnIndex(Integer index) {
    return (String) entrySet().toArray()[index];
  }
}
