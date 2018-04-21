package org.ranger.service;

import java.util.List;
import java.util.Map;

public interface StudentService {

	Map<String, Object> addStudent(Map<String, Object> entity);
	boolean deleteStudentById(Long id);
	Map<String, Object> updateStudent(Map<String, Object> entity);
	Map<String, Object> findStudentById(Long id);
	List<Map<String, Object>> findStudentByIds(Long ids);
	List<Map<String, Object>> findStudentByEntityLikePage(Map<String, Object> entity);

	
	
}
