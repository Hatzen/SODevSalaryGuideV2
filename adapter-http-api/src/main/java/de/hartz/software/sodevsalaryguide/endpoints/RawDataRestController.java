package de.hartz.software.sodevsalaryguide.endpoints;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@RestController
@RequestMapping("/api/v1/rawdata")
class RawDataRestController {

  @GetMapping(value = "/csv/{csvName}")
  public ResponseEntity<StreamingResponseBody> getCsvFile(@PathVariable String csvName) {
    StreamingResponseBody stream =
        output -> {
          // TODO: Read file from resources
          Writer writer = new BufferedWriter(new OutputStreamWriter(output));
          writer.write("name,rollNo" + "\n");
          for (int i = 1; i <= 10000; i++) {
            writer.write("Name" + i + "," + i + "\n");
            writer.flush();
          }
        };
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv")
        .contentType(MediaType.TEXT_PLAIN)
        .body(stream);
  }
}
