package org.ranger.service;

import java.util.List;
import java.util.Map;

public interface StudentIdentiferService {

	Map<String, Object> addStudentIdentifer(Map<String, Object> entity);
	boolean deleteStudentIdentiferById(Long id);
	Map<String, Object> updateStudentIdentifer(Map<String, Object> entity);
	Map<String, Object> findStudentIdentiferById(Long id);
	List<Map<String, Object>> findStudentIdentiferByIds(Long ids);
	List<Map<String, Object>> findStudentIdentiferByEntityLikePage(Map<String, Object> entity);

	
	
}
