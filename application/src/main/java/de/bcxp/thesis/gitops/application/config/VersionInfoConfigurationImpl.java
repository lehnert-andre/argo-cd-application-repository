package de.bcxp.thesis.gitops.application.config;

import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.configuration.VersionInfoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@ConfigurationProperties(prefix = "application.version-info")
@ConstructorBinding
public class VersionInfoConfigurationImpl implements VersionInfoConfiguration {

  private final String name;
  private final String environment;
  private final String version;
  private final String example;

  public VersionInfoConfigurationImpl(String name, String environment, String version, String example) {
    this.name = name;
    this.environment = environment;
    this.version = version;
    this.example = example;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getEnvironment() {
    return environment;
  }

  @Override
  public String getVersion() {
    return version;
  }

  @Override
  public String getExample() {
    return example;
  }
}
