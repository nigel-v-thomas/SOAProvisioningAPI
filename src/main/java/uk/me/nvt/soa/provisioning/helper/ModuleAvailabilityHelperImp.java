package uk.me.nvt.soa.provisioning.helper;

import org.springframework.stereotype.Service;


import uk.me.nvt.soa.provisioning.model.ModuleAvailability;
@Service
public class ModuleAvailabilityHelperImp implements ModuleAvailabilityHelper {

	@Override
	public void updateModuleAvailability(String moduleId,
			ModuleAvailability moduleAvailability) {
		// TODO Auto-generated method stub

	}

	@Override
	public ModuleAvailability getModuleAvailability(String moduleId) {
		// TODO Auto-generated method stub
		return new ModuleAvailability();
	}

}
