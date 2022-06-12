package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types;

import de.bcxp.thesis.common.errorhandling.exception.TechnicalException;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.errorcode.VersionInfoErrorCode;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public enum ApplicationEnvironment {
  LOCAL,
  PROD,
  ;

  public static ApplicationEnvironment fromString(String applicationEnvironmentString) {
    for (ApplicationEnvironment applicationEnvironment : ApplicationEnvironment.values()) {
      if (applicationEnvironmentString != null
          && applicationEnvironmentString.equalsIgnoreCase(applicationEnvironment.name())) {
        return applicationEnvironment;
      }
    }

    throw new TechnicalException(VersionInfoErrorCode.UNKNOWN_ENVIRONMENT,
        String.format("Unknown environment name '%s'", applicationEnvironmentString));
  }
}
