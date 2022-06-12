package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.configuration;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public interface VersionInfoConfiguration {

  String getName();
  String getEnvironment();
  String getVersion();
}
