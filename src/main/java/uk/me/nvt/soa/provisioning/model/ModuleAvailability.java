package uk.me.nvt.soa.provisioning.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModuleAvailability {
	
	/**
	   * Minimum class size.
	   * The value may not be {@code null}.
	   */
	  private String minClassSize;

	  /**
	   * Maximum class size.
	   * The value may not be {@code null}.
	   */
	  private String maxClassSize;

	  /**
	   * Year module taught in yyyy format.
	   * The value may not be {@code null}.
	   */
	  private String yearModuleTaught;

	  /**
	   * Module start date
	   * The value may not be {@code null}.
	   */
	  private String moduleStartDate;

	  /**
	   * Module end date
	   * The value may not be {@code null}.
	   */
	  private String moduleEndDate;


	@XmlElement(nillable=false, type=String.class)
	public String getMinClassSize() {
		return minClassSize;
	}

	public void setMinClassSize(String minClassSize) {
		this.minClassSize = minClassSize;
	}

	@XmlElement(nillable=false, type=String.class)
	public String getMaxClassSize() {
		return maxClassSize;
	}

	public void setMaxClassSize(String maxClassSize) {
		this.maxClassSize = maxClassSize;
	}

	@XmlElement(nillable=false, type=String.class)
	public String getYearModuleTaught() {
		return yearModuleTaught;
	}

	public void setYearModuleTaught(String yearModuleTaught) {
		this.yearModuleTaught = yearModuleTaught;
	}

	@XmlElement(nillable=false, type=String.class)
	public String getModuleStartDate() {
		return moduleStartDate;
	}

	public void setModuleStartDate(String whoCanViewMembership) {
		this.moduleStartDate = whoCanViewMembership;
	}

	@XmlElement(nillable=false, type=String.class)
	public String getModuleEndDate() {
		return moduleEndDate;
	}

	public void setModuleEndDate(
			String moduleEndDate) {
		this.moduleEndDate = moduleEndDate;
	}

	@Override
	public String toString() {
		return "ModuleAvailability [minClassSize=" + minClassSize
				+ ", maxClassSize=" + maxClassSize + ", yearModuleTaught="
				+ yearModuleTaught + ", moduleStartDate="
				+ moduleStartDate
				+ ", moduleEndDate="
				+ moduleEndDate +  "]";
	}

}
