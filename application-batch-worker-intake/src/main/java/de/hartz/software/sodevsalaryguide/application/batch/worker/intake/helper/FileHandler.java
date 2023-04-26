package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.helper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import de.hartz.software.sodevsalaryguide.core.model.raw.HeaderMetaUntyped;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {

  private final Logger logger = LoggerFactory.getLogger(FileHandler.class);

  private final RawDataSetName rawDataSetName;
  private CSVReader cSVReader;
  private List<HeaderMetaUntyped> headerMetaUntyped;

  public FileHandler(RawDataSetName rawDataSetName) throws Exception {
    this.rawDataSetName = rawDataSetName;
    initReader();
    initHeaders();
  }

  public RawRow readLine() {
    try {
      String[] line = cSVReader.readNext();
      if (line == null) return null;
      RawRow rawRow = new RawRow(rawDataSetName);
      for (int i = 0; i < line.length; i++) {
        rawRow.put(headerMetaUntyped.get(i), line[i]);
      }
      return rawRow;
    } catch (Exception e) {
      logger.error("Error while reading line in file: " + this.rawDataSetName);
      return null;
    }
  }

  private void initHeaders() throws IOException, CsvValidationException {
    headerMetaUntyped = new ArrayList<>();
    String[] line = cSVReader.readNext();
    for (String header : line) {
      headerMetaUntyped.add(new HeaderMetaUntyped(header));
    }
  }

  private void initReader() throws Exception {
    ClassLoader classLoader = this.getClass().getClassLoader();
    // TODO: Get by http
    File file = new File(classLoader.getResource(rawDataSetName.getFileName()).getFile());
    cSVReader = new CSVReader(new FileReader(file));
  }

  public void closeReader() {
    try {
      cSVReader.close();
    } catch (IOException e) {
      logger.error("Error while closing reader.");
    }
  }
}
