package de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api;

import de.bcxp.thesis.common.marker.BusinessComponent;
import de.bcxp.thesis.gitops.components.versioninfo.businesslogic.api.types.VersionInfo;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
public interface VersionInfoComponent extends BusinessComponent {

  VersionInfo getVersionInfo();
}
