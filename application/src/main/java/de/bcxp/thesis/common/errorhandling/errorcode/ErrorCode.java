package de.bcxp.thesis.common.errorhandling.errorcode;

public interface ErrorCode {

    ErrorCategory getErrorCategory();

    String getNamespace();

    String getName();

    String getDescription();

    default String getErrorId() {
        return String.format("%s.%s", getNamespace().toUpperCase(), getName().toUpperCase());
    }
}
