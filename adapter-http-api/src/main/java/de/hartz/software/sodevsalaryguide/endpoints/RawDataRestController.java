package de.hartz.software.sodevsalaryguide.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/rawdata")
class RawDataRestController {

  @GetMapping(value = "/csv/{csvName}")
  public ResponseEntity<Resource> getCsvFile(@PathVariable String csvName) {
    ClassPathResource resource = new ClassPathResource("csvchunks/" + csvName + ".csv");

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvName + ".csv")
        .contentType(MediaType.TEXT_PLAIN)
        .body(resource);
  }
}
