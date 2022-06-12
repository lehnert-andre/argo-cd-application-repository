package de.bcxp.thesis.gitops.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@ConfigurationProperties(prefix = "application")
@ConstructorBinding
public class ApplicationConfiguration {

  public ApplicationConfiguration() {
  }

}
