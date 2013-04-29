package uk.me.nvt.soa.provisioning.model;

import java.net.URI;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

import uk.me.nvt.soa.provisioning.resources.StudentResource;

@XmlRootElement
public class Student {
	@Ref(style=Style.ABSOLUTE,value="students/${instance.id}")
    @XmlElement
	public URI self;
	private String id;
	
	private String loginUsername;
	@XmlTransient
	private boolean isActive = true;
	private String hostInstitutionId;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result
				+ ((loginUsername == null) ? 0 : loginUsername.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive != other.isActive)
			return false;
		if (loginUsername == null) {
			if (other.loginUsername != null)
				return false;
		} else if (!loginUsername.equals(other.loginUsername))
			return false;
		return true;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	
	@XmlTransient
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getHostInstitutionId() {
		return hostInstitutionId;
	}

	public void setHostInstitutionId(String hostInstitutionId) {
		this.hostInstitutionId = hostInstitutionId;
	}
}
