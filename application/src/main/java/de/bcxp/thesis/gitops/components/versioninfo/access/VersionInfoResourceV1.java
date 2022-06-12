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

@Tag(name = "1) Version Information")
@RestController
@RequestMapping("/v1/version")
public class VersionInfoResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(VersionInfoResourceV1.class);

  private final VersionInfoComponent versionInfoComponent;

  @Autowired
  public VersionInfoResourceV1(VersionInfoComponent versionInfoComponent) {
    this.versionInfoComponent = versionInfoComponent;
  }

  @Operation(
      summary = "Get version information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Version information with the name and the current version of the application.",
              content = @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VersionInfoTO.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "",
              content = @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorTO.class),
                  examples = {
                      @ExampleObject(value = """
                                             {
                                             "error": "GLOBAL.INVALID_ARGUMENT",
                                             "error_description": "Please define the parameter 'file.file'"
                                             }
                                             """)

                  }
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "",
              content = @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorTO.class),
                  examples = {
                      @ExampleObject(value = """
                                             {
                                             "error": "GLOBAL.INTERNAL_SERVER_ERROR",
                                             "error_description": "Unexpected error occured."
                                             }
                                             """)

                  }
              )
          )
      })
  @RequestMapping(method = RequestMethod.GET)
  public VersionInfoTO getVersionInfo() {
    final VersionInfo versionInfo = versionInfoComponent.getVersionInfo();

    LOG.info("Map version info with name={}, environment={} and version={}",
        versionInfo.name(), versionInfo.applicationEnvironment().name(), versionInfo.version());

    return new VersionInfoTO(versionInfo.name(), versionInfo.applicationEnvironment().name(), versionInfo.version());
  }
}
