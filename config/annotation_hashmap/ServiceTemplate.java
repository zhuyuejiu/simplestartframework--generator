package ${package_prefix}.service;

import java.util.List;
import java.util.Map;

public interface ${className}Service {

	Map<String, Object> add${className}(Map<String, Object> entity);
	boolean delete${className}ById(Long id);
	Map<String, Object> update${className}(Map<String, Object> entity);
	Map<String, Object> find${className}ById(Long id);
	List<Map<String, Object>> find${className}ByIds(Long ids);
	List<Map<String, Object>> find${className}ByEntityLikePage(Map<String, Object> entity);

	
	
}
