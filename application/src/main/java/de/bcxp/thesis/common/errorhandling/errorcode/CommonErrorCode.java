package de.bcxp.thesis.common.errorhandling.errorcode;

/**
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public enum CommonErrorCode implements ErrorCode {
  ;

  private final String namespace;
  private final ErrorCategory errorCategory;
  private final String description;

  CommonErrorCode(String namespace, ErrorCategory errorCategory, String description) {
    this.namespace = namespace;
    this.errorCategory = errorCategory;
    this.description = description;
  }

  @Override
  public ErrorCategory getErrorCategory() {
    return this.errorCategory;
  }

  @Override
  public String getNamespace() {
    return this.namespace.toUpperCase();
  }

  @Override
  public String getName() {
    return this.name().toUpperCase();
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
