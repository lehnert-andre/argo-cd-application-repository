package de.bcxp.thesis.gitops.application.errorhandling;


import de.bcxp.thesis.common.errorhandling.errorcode.GlobalErrorCode;
import de.bcxp.thesis.common.errorhandling.exception.BusinessException;
import de.bcxp.thesis.common.errorhandling.exception.TechnicalException;
import de.bcxp.thesis.gitops.application.errorhandling.types.ErrorTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * I handle all application exceptions globally and generate an error response with a suitable http status code and an
 * error message
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh 
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ExceptionHandler(BusinessException.class)
  public final ResponseEntity<ErrorTO> handleBusinessException(final BusinessException ex,
                                                               final WebRequest request) {
    final HttpStatus errorStatus;
    switch (ex.getErrorCode().getName()) {
      default:
        errorStatus = HttpStatus.BAD_REQUEST;

    }
    final ErrorTO errorDetails = new ErrorTO(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorDetails, errorStatus);
  }


  /**
   * Handles all {@link TechnicalException}
   *
   * @param ex      {@link TechnicalException}
   * @param request will be ignored
   *
   * @return response with error
   */
  @ExceptionHandler(TechnicalException.class)
  public final ResponseEntity<ErrorTO> handleTechnicalException(final TechnicalException ex,
                                                                final WebRequest request) {
    LOG.error(ex.getMessage(), ex);

    final HttpStatus errorStatus;
    switch (ex.getErrorCode().getName()) {
      default:
        errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        break;
    }
    final ErrorTO errorDetails = new ErrorTO(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorDetails, errorStatus);
  }

  /**
   * Handles all {@link RuntimeException}
   *
   * @param ex      {@link RuntimeException}
   * @param request will be ignored
   *
   * @return response with error
   */
  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<ErrorTO> handleRuntimeException(final RuntimeException ex,
                                                              final WebRequest request) {
    LOG.error(ex.getMessage(), ex);

    final ErrorTO errorDetails = new ErrorTO(GlobalErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles invalid REST requests
   *
   * @return response with error
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                final HttpHeaders headers, final HttpStatus status,
                                                                final WebRequest request) {
    LOG.error(ex.getMessage(), ex);

    final List<ErrorTO> errors = new ArrayList<>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(new ErrorTO(GlobalErrorCode.INVALID_ARGUMENT_ERROR));
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(new ErrorTO(GlobalErrorCode.INVALID_ARGUMENT_ERROR));
    }

    return handleExceptionInternal(
        ex, errors, headers, HttpStatus.BAD_REQUEST, request);
  }
}
