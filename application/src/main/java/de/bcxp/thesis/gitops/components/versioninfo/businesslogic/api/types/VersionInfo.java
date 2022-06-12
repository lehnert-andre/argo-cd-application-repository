package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public record VersionInfo(String name, ApplicationEnvironment applicationEnvironment, String version) {
}
