package uk.me.nvt.soa.provisioning.helper;

import uk.me.nvt.soa.provisioning.model.ModuleAvailability;

public interface ModuleAvailabilityHelper {

	void updateModuleAvailability(String moduleId,
			ModuleAvailability moduleAvailability);

	ModuleAvailability getModuleAvailability(String moduleId);

}
