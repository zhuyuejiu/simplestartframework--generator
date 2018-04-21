package org.ranger.service;

import java.util.List;
import java.util.Map;

public interface TeacherService {

	Map<String, Object> addTeacher(Map<String, Object> entity);
	boolean deleteTeacherById(Long id);
	Map<String, Object> updateTeacher(Map<String, Object> entity);
	Map<String, Object> findTeacherById(Long id);
	List<Map<String, Object>> findTeacherByIds(Long ids);
	List<Map<String, Object>> findTeacherByEntityLikePage(Map<String, Object> entity);

	
	
}
