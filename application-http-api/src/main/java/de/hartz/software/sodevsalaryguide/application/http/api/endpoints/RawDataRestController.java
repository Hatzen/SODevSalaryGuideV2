package de.hartz.software.sodevsalaryguide.application.http.api.endpoints;

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
        ClassPathResource resource = new ClassPathResource("csvchunks/" + csvName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvName)
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    // @RateLimiting(
    //         // reference to the property file
    //         name = "not_an_admin",
    //         // the rate limit is per user
    //         cacheKey = "#username",
    //         // only when the parameter is not admin
    //         executeCondition = "#username != 'admin'",
    //         // skip when parameter equals admin
    //         skipCondition = "#username eq 'admin",
    //         // the method name is added to the cache key to  prevent conflicts with other methods
    //         ratePerMethod = true,
    //         // if the limit is exceeded the fallback method is called. If not provided an exception is thrown
    //         fallbackMethodName = "myFallbackMethod")
    // public String execute(String username) {
    //     log.info("Method with Param {} executed", username);
    //     return myParamName;
    // }
//
    // // the fallback method must have the same signature
    // public String myFallbackMethod(String username) {
    //     log.info("Fallback-Method with Param {} executed", username);
    //     return myParamName;
    // }
}
