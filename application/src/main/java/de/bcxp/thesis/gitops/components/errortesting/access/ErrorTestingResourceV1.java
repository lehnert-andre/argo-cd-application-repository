package de.bcxp.thesis.gitops.components.errortesting.access;

import de.bcxp.thesis.common.errorhandling.errorcode.ErrorCategory;
import de.bcxp.thesis.common.errorhandling.errorcode.GlobalErrorCode;
import de.bcxp.thesis.common.errorhandling.exception.BusinessException;
import de.bcxp.thesis.common.errorhandling.exception.TechnicalException;
import de.bcxp.thesis.gitops.application.errorhandling.types.ErrorTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Utility")
@RestController
@RequestMapping("/v1/error")
public class ErrorTestingResourceV1 {

  private static final Logger LOG = LoggerFactory.getLogger(ErrorTestingResourceV1.class);

  @Operation(
      summary = "Upload and create a new on-premise signature document",
      responses = {
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
          )
      })
  @RequestMapping(method = RequestMethod.GET, value = "/provoke/{errorCode}")
  public String generateErrorCode(
      @Parameter(allowEmptyValue = true, examples = {
          @ExampleObject(name = "BUSINESS", value = "BUSINESS"),
          @ExampleObject(name = "TECHNICAL", value = "TECHNICAL"),
          @ExampleObject(name = "EMPTY_VALUE", value = ""),
          @ExampleObject(name = "OTHER", value = "OTHER"),
      })
      @PathVariable("errorCode") final String errorCode) {

    LOG.debug("Receive 'generateErrorCode' request with {}", errorCode);

    if (errorCode == null) {
      throw new NullPointerException("NullPointerException: Error not found");
    } else if (errorCode.equalsIgnoreCase(ErrorCategory.BUSINESS.name())) {
      throw new BusinessException(GlobalErrorCode.INVALID_ARGUMENT_ERROR, "Simulated business exception");
    } else if (errorCode.equalsIgnoreCase(ErrorCategory.TECHNICAL.name())) {
      throw new TechnicalException(GlobalErrorCode.INTERNAL_SERVER_ERROR, "Simulated technical exception");
    } else {
      throw new RuntimeException("RuntimeException: Undefined error");
    }
  }
}
