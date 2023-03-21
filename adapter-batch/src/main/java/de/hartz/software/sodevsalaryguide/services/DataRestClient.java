package de.hartz.software.sodevsalaryguide.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// https://www.baeldung.com/spring-resttemplate-download-large-file
@Service
public class DataRestClient {
  @Autowired RestTemplateBuilder restTemplateBuilder;

  private String HOST = "http://localhost:8080/";
  private String PATH = "/api/v1/rawdata/csv/";

  // TODO:
  // https://stackoverflow.com/questions/51845228/proper-way-of-streaming-using-responseentity-and-making-sure-the-inputstream-get
  public DataRestClient() {
    String csvName = "testCsv.csv";
    ResponseEntity<Resource> response =
        restTemplateBuilder.build().getForEntity(HOST + PATH + "/" + csvName, Resource.class);
    // Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
  }
}
