package de.bcxp.thesis.gitops.components.versioninfo.access;

import de.bcxp.thesis.gitops.application.errorhandling.types.ErrorTO;
import de.bcxp.thesis.gitops.components.versioninfo.access.types.VersionInfoTO;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.VersionInfoComponent;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types.VersionInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "1) Information")
@RestController
@RequestMapping("/v1/ping")
public class PingResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(PingResourceV1.class);

  @Autowired
  public PingResourceV1() {
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getPing() {
    return "OK";
  }
}
