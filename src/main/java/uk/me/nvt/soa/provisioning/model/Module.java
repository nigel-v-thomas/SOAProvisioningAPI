package uk.me.nvt.soa.provisioning.model;

import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

@XmlRootElement
@XmlType(propOrder = { "self", "id", "name", "description", "moduleURL", "courses"})
public class Module {
	@Ref(style=Style.ABSOLUTE,value="modules/${instance.id}")
    @XmlElement
	public URI self;	
	private String id;
	private String name;
	private String description;
	private String moduleURL;

	@XmlTransient 
	private List<String> courseList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String getModuleURL() {
		return moduleURL;
	}

	public void setModuleURL(String emailPermission) {
		this.moduleURL = emailPermission;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCourses() == null) ? 0 : getCourses().hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((moduleURL == null) ? 0 : moduleURL.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Module other = (Module) obj;
		if (getCourses() == null) {
			if (other.getCourses() != null)
				return false;
		} else if (!getCourses().equals(other.getCourses()))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moduleURL == null) {
			if (other.moduleURL != null)
				return false;
		} else if (!moduleURL.equals(other.moduleURL))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@XmlElementWrapper(name="courses")
	@XmlElement(name="kisCourseId")
	public List<String> getCourses() {
		return courseList;
	}

	public void setCourses(List<String> courses) {
		this.courseList = courses;
	}


}
