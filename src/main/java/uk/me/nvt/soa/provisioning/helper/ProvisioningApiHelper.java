package uk.me.nvt.soa.provisioning.helper;

import java.util.List;

import uk.me.nvt.soa.provisioning.model.Module;
import uk.me.nvt.soa.provisioning.model.Student;
import uk.me.nvt.soa.provisioning.model.Students;

public interface ProvisioningApiHelper {

	void addStudent(String id);

	Student getStudent(String id);

	Module getModule(String moduleId);

	void updateModule(Module module);

	void deleteModule(String moduleId);

	List<Module> listAllModules();

	void createModule(Module module);

	Students listAllStudents();

	void removeStudent(String memberId);

	void creatStudent(Student student);

}
