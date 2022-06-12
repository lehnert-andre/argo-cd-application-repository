package de.bcxp.thesis.common.errorhandling.exception;


import de.bcxp.thesis.common.errorhandling.errorcode.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Base exception class
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
abstract class CoreException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(CoreException.class);

    private final ErrorCode errorCode;
    private final String[] parameters;


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     */
    CoreException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
        this.parameters = new String[0];

        LOG.debug("{}: '{}'", errorCode.getErrorId(), message);
    }


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param cause     error cause
     */
    CoreException(final ErrorCode errorCode, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.parameters = new String[0];

        LOG.debug("{}: with stacktrace {}", errorCode.getErrorId(), stacktrace2String(cause));
    }


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param cause     error cause
     */
    CoreException(final ErrorCode errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.parameters = new String[0];

        LOG.debug("{}: '{}' with stacktrace {}", errorCode.getErrorId(), message, stacktrace2String(cause));
    }


    /**
     * CoreException
     *
     * @param errorCode          specifies the detailed error
     * @param message            error message
     * @param cause              error cause
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     */
    CoreException(final ErrorCode errorCode, final String message, final Throwable cause,
                  final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.parameters = new String[0];

        if (LOG.isDebugEnabled()) {
            LOG.debug("{}: '{}' with stacktrace {}", errorCode.getErrorId(), message, stacktrace2String(cause));
        }
    }


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param param     error parameter to be logged and displayed with the error code
     */
    CoreException(final ErrorCode errorCode, final String message, final Object... param) {
        super(message);
        this.errorCode = errorCode;
        this.parameters = objectParametersToStringArray(param);

        if (LOG.isDebugEnabled()) {
            LOG.debug("{}: '{}' with parameters [{}]", errorCode.getErrorId(), message, String.join(",", this.parameters));
        }
    }


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param param     error parameter to be logged and displayed with the error code
     * @param cause     error cause
     */
    CoreException(final ErrorCode errorCode, final Throwable cause, final Object... param) {
        super(cause);
        this.errorCode = errorCode;
        this.parameters = objectParametersToStringArray(param);

        if (LOG.isDebugEnabled()) {
            LOG.debug("{}: with parameters [{}] and stacktrace {}", errorCode.getErrorId(),
                String.join(",", this.parameters), stacktrace2String(cause));
        }
    }


    /**
     * CoreException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param param     error parameter to be logged and displayed with the error code
     * @param cause     error cause
     */
    CoreException(final ErrorCode errorCode, final String message, final Throwable cause, final Object... param) {
        super(message, cause);
        this.errorCode = errorCode;
        this.parameters = objectParametersToStringArray(param);

        if (LOG.isDebugEnabled()) {
            LOG.debug("{}: '{}' with parameters [{}] and stacktrace {}", errorCode.getErrorId(), message,
                String.join(",", this.parameters), stacktrace2String(cause));
        }
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public String[] getParameters() {
        return parameters;
    }


    private static String[] objectParametersToStringArray(final Object... param) {
        return Arrays.stream(param).map(String::valueOf).toArray(String[]::new);
    }


    private static String stacktrace2String(final Throwable e) {
        if (e != null) {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(stream));
            return stream.toString();
        } else {
            return "[no stacktrace found]";
        }
    }
}
