package de.bcxp.thesis.gitops.application.errorhandling.types;

import de.bcxp.thesis.common.errorhandling.errorcode.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public class ErrorTO {

  @Schema(
      name = "error",
      example = "GLOBAL.EXTERNAL_SERVICE_UNAVAILABLE")
  private final String errorCode;

  @Schema(name = "error_description")
  private final String errorDescription;


  /**
   * Constructor with all parameter
   *
   * @param errorCode    to identify the specific error and translate the error on the user interface
   * @param errorDescription optional errorMessage of the exception (can be removed in a later version of the project)
   */
  public ErrorTO(final ErrorCode errorCode, final String errorDescription) {
    this.errorCode = errorCode.getErrorId();
    this.errorDescription = errorDescription;
  }

  public ErrorTO(final ErrorCode errorCode) {
    this.errorCode = errorCode.getErrorId();
    errorDescription = null;
  }


  public String getErrorCode() {
    return errorCode;
  }


  public String getErrorDescription() {
    return errorDescription;
  }
}
