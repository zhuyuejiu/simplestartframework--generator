package org.ranger.service;

import java.util.List;
import java.util.Map;

public interface StudentTeacherService {

	Map<String, Object> addStudentTeacher(Map<String, Object> entity);
	boolean deleteStudentTeacherById(Long id);
	Map<String, Object> updateStudentTeacher(Map<String, Object> entity);
	Map<String, Object> findStudentTeacherById(Long id);
	List<Map<String, Object>> findStudentTeacherByIds(Long ids);
	List<Map<String, Object>> findStudentTeacherByEntityLikePage(Map<String, Object> entity);

	
	
}
