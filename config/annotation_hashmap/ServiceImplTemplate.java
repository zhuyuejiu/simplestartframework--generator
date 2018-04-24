package ${package_prefix}.service.impl;

import java.util.List;
import java.util.Map;


import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.component.Service;
import org.simplestartframwork.data.annotation.Transaction;

import ${package_prefix}.mapper.${className}Mapper;
import ${package_prefix}.service.${className}Service;

@Service
public class ${className}ServiceImpl implements ${className}Service{
	
	@Inject
	private ${className}Mapper ${feildName}Mapper;
	
	/**
	 * 增加模块
	 * @param entity
	 * @return
	 */
	@Transaction
	@Override
	public Map<String,Object> add${className}(Map<String,Object> entity) {
		// TODO Auto-generated method stub
		return null;
		
	}


	@Transaction
	@Override
	public boolean delete${className}ById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transaction
	@Override
	public Map<String, Object> update${className}(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> find${className}ById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> find${className}ByIds(Long ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> find${className}ByEntityLikePage(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
