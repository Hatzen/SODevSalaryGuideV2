package de.hartz.software.sodevsalaryguide.core.model.raw;

import de.hartz.software.sodevsalaryguide.core.model.Computation;
import java.util.HashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RawRow extends HashMap<HeaderMeta<?>, String> {
  @Getter private final RawDataSetName rawDataSetName;
  @Getter @Setter private Computation computation;

  public String get(String columName) {
    return getValueForColumnName(columName);
  }

  public String getValueForColumnName(String columName) {
    // TODO: is there a better and performant way?
    return super.get(new HeaderMeta<>(columName, null));
  }

  // TODO: This will obviously not work deterministicly with Hash implementation
  public String getValueForColumnIndex(Integer index) {
    return (String) entrySet().toArray()[index];
  }
}
