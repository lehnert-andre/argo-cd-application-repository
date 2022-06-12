package de.bcxp.thesis.gitops.application.errorhandling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle 404 Whitelabel Error Page
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@RestController
public class CustomErrorController implements ErrorController {

  private static final Logger LOG = LoggerFactory.getLogger(CustomErrorController.class);

  @GetMapping("/error")
  public ResponseEntity<String> customError() {
    LOG.warn("Handling /error and return 404");
    return ResponseEntity.notFound().build();
  }
}
