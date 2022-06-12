package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.errorcode;

import de.bcxp.thesis.common.errorhandling.errorcode.ErrorCategory;
import de.bcxp.thesis.common.errorhandling.errorcode.ErrorCode;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public enum VersionInfoErrorCode implements ErrorCode {

  UNKNOWN_ENVIRONMENT(ErrorCategory.TECHNICAL, "The given environment name is unknown."),
  ;

  private final static String NAMESPACE = "VERSION_INFO";
  private final ErrorCategory errorCategory;
  private final String description;

  VersionInfoErrorCode(ErrorCategory errorCategory, String description) {
    this.errorCategory = errorCategory;
    this.description = description;
  }

  @Override
  public ErrorCategory getErrorCategory() {
    return this.errorCategory;
  }

  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getDescription() {
    return this.description;
  }

}
