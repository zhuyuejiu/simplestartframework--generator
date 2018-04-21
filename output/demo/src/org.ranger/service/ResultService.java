package org.ranger.service;

import java.util.List;
import java.util.Map;

public interface ResultService {

	Map<String, Object> addResult(Map<String, Object> entity);
	boolean deleteResultById(Long id);
	Map<String, Object> updateResult(Map<String, Object> entity);
	Map<String, Object> findResultById(Long id);
	List<Map<String, Object>> findResultByIds(Long ids);
	List<Map<String, Object>> findResultByEntityLikePage(Map<String, Object> entity);

	
	
}
