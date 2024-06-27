package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

// https://www.baeldung.com/spring-resttemplate-download-large-file
@Service
public class DataRestClient {
    public static final String HOST = "http://ui-backend:8121/";
    public static final String PATH = "/api/v1/rawdata/csv/";
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    // https://stackoverflow.com/questions/51845228/proper-way-of-streaming-using-responseentity-and-making-sure-the-inputstream-get
    public InputStream getFileForDatasetName(RawDataSetName rawDataSetName) {
        ResponseEntity<Resource> response =
                restTemplateBuilder
                        .build()
                        .getForEntity(HOST + PATH + "/" + rawDataSetName.getFileName(), Resource.class);
        try {
            return response.getBody().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
