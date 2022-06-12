package de.bcxp.thesis.common.errorhandling.errorcode;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public enum GlobalErrorCode implements ErrorCode {

  EXTERNAL_SERVICE_UNAVAILABLE(ErrorCategory.TECHNICAL, "Description"),
  INTERNAL_SERVER_ERROR(ErrorCategory.TECHNICAL, "An unexpected error occurred."),

  INVALID_ARGUMENT_ERROR(ErrorCategory.BUSINESS, "Invalid argument."),
  RESOURCE_NOT_FOUND(ErrorCategory.BUSINESS, "Resource not found.");


  private final static String NAMESPACE = "GLOBAL";
  private final ErrorCategory errorCategory;
  private final String description;

  GlobalErrorCode(ErrorCategory errorCategory, String description) {
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
