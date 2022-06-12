package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.impl;

import de.bcxp.thesis.common.validation.Precondition;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.VersionInfoComponent;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.configuration.VersionInfoConfiguration;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types.ApplicationEnvironment;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@Component
public class VersionInfoComponentImpl implements VersionInfoComponent {

  private static final Logger LOG = LoggerFactory.getLogger(VersionInfoComponentImpl.class);

  private final VersionInfoConfiguration versionInfoConfiguration;

  @Autowired
  public VersionInfoComponentImpl(VersionInfoConfiguration versionInfoConfiguration) {
    this.versionInfoConfiguration = versionInfoConfiguration;
  }

  @Override
  public VersionInfo getVersionInfo() {
    final String name = versionInfoConfiguration.getName();
    final String version = versionInfoConfiguration.getVersion();
    final ApplicationEnvironment applicationEnvironment = ApplicationEnvironment.fromString(
        versionInfoConfiguration.getEnvironment());

    Precondition.checkNotNullOrEmpty(name, "VersionInfo name must not be null or empty");
    Precondition.checkNotNullOrEmpty(version, "VersionInfo version must not be null or empty");
    Precondition.checkNotNull(applicationEnvironment, "VersionInfo applicationEnvironment must not be null");

    LOG.info("Read version info from config with name={}, environment={} and version={}",
        name, applicationEnvironment, version);

    return new VersionInfo(name, applicationEnvironment, version);
  }

}
