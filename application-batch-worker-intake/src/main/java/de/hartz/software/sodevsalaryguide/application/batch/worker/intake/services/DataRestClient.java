package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// https://www.baeldung.com/spring-resttemplate-download-large-file
@Service
public class DataRestClient {
  public static final String HOST = "http://localhost:8080/";
  public static final String PATH = "/api/v1/rawdata/csv/";
  @Autowired RestTemplateBuilder restTemplateBuilder;

  // https://stackoverflow.com/questions/51845228/proper-way-of-streaming-using-responseentity-and-making-sure-the-inputstream-get
  public File getFileForDatasetName(RawDataSetName rawDataSetName) {
    ResponseEntity<Resource> response =
        restTemplateBuilder
            .build()
            .getForEntity(HOST + PATH + "/" + rawDataSetName.getFileName(), Resource.class);
    try {
      return response.getBody().getContentAsByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
