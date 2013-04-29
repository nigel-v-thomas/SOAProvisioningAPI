package uk.me.nvt.soa.provisioning.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;


import uk.me.nvt.soa.provisioning.model.Module;
import uk.me.nvt.soa.provisioning.model.Student;
import uk.me.nvt.soa.provisioning.model.Students;

@Service
public class ProvisioningApiHelperImpl implements ProvisioningApiHelper {
	private static ConcurrentMap<String, Student> students = new ConcurrentHashMap<String, Student>();
	private static ConcurrentMap<String, Module> modules = new ConcurrentHashMap<String, Module>();

	@Override
	public void addStudent(String studentId) {
		Student st = new Student();
		st.setId(studentId);
		students.put(studentId, st);
	}

	@Override
	public synchronized void removeStudent(String studentId) {
		students.remove(studentId);
	}

	@Override
	public Student getStudent(String studentId) {
		return students.get(studentId);
	}

	@Override
	public Module getModule(String moduleId) {
		Module md = new Module();
		md.setId(moduleId);
		return md;
	}

	@Override
	public Students listAllStudents() {
		Students students = new Students();
		students.setStudentList(new ArrayList<Student>(ProvisioningApiHelperImpl.students.values()));
		return students;
	}

	@Override
	public void updateModule(Module module) {
		Module m = modules.remove(module.getId());
		modules.put(module.getId(), module);
	}

	@Override
	public void deleteModule(String moduleId) {
		modules.remove(moduleId);
	}

	@Override
	public List<Module> listAllModules() {
		return new ArrayList<Module>(modules.values());
	}

	@Override
	public void createModule(Module module) {
		modules.put(module.getId(), module);
	}

	@Override
	public void creatStudent(Student student) {
		students.put(student.getId(), student);
	}



}
