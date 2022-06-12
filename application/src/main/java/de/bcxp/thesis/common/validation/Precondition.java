package de.bcxp.thesis.common.validation;

import de.bcxp.thesis.common.errorhandling.errorcode.GlobalErrorCode;
import de.bcxp.thesis.common.errorhandling.exception.BusinessException;

/**
 * I perform precondition checks.
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 * @see <a href="https://en.wikipedia.org/wiki/Fail-fast">Wikipedia - Fail-fast</a>
 * @see <a href="https://en.wikipedia.org/wiki/Design_by_contract">Wikipedia - Design by contract</a>
 * @see <a href="https://docs.oracle.com/cd/E19683-01/806-7930/assert-13/index.html">
 * Preconditions, Postconditions, and Class Invariants</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html">
 * Programming With Assertions</a>
 */
public final class Precondition {

  private Precondition() {

  }


  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference    an object reference
   * @param errorMessage the exception message to use if the check fails
   * @return the non-null reference that was validated
   * @throws BusinessException if {@code reference} is null
   */
  public static <T> T checkNotNull(final T reference, final String errorMessage) {
    if (reference == null) {
      throw new BusinessException(GlobalErrorCode.INVALID_ARGUMENT_ERROR, errorMessage);
    }

    return reference;
  }


  /**
   * Ensures that a String passed as a parameter to the calling method is not null or empty.
   *
   * @param parameter    an object reference
   * @param errorMessage the exception message to use if the check fails
   * @return the non-null String that was validated
   * @throws BusinessException if {@code parameter} is null or empty
   */
  public static String checkNotNullOrEmpty(final String parameter, final String errorMessage) {
    if (parameter == null || parameter.length() == 0) {
      throw new BusinessException(GlobalErrorCode.INVALID_ARGUMENT_ERROR, errorMessage);
    }

    return parameter;
  }


  /**
   * Ensures that a boolean expression passed as a parameter to the calling method is true.
   *
   * @param expression   a boolean expression
   * @param errorMessage the exception message to use if the check fails
   * @throws BusinessException if {@code expression} is false
   */
  public static void checkArgument(final boolean expression,
                                   final String errorMessage) {
    if (!expression) {
      throw new BusinessException(GlobalErrorCode.INVALID_ARGUMENT_ERROR, errorMessage);
    }
  }
}
