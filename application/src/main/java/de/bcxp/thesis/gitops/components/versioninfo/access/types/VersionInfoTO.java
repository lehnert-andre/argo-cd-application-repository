package de.bcxp.thesis.gitops.components.versioninfo.access.types;

import de.bcxp.thesis.common.marker.TransferObject;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public record VersionInfoTO (String name, String environment, String version) implements TransferObject {

}
