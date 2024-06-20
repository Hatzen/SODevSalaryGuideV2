package de.hartz.software.sodevsalaryguide.core.model.raw;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// No Args constructor needed for deserialization via Jackson
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class RawDataSetName {
  private String fileName;
  private int year;

  // TODO: Setter not needed but constructor is not useful? maybe use bean validation?
  public void setFileName(String fileName) {
    assert !fileName.contains(".csv") : "the filename should not contain the extension";
    this.fileName = fileName;
  }

  public int getChunk() {
    Pattern patternForDigits = Pattern.compile("[0-9]+$");
    Matcher matcher = patternForDigits.matcher(fileName);
    if (!matcher.find()) {
      return -1;
    }
    return Integer.parseInt(matcher.group());
  }
}
