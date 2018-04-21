package ${package_prefix}.service.impl;

import java.util.List;
import java.util.Map;

import org.simplestartframwork.context.annotation.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gb.mapper.ModularMapper;
import com.gb.service.ModularService;

@Service
public class ${className}ServiceImpl implements ModularService{
	
	@Inject
	private ${className}Mapper ${feildName}Mapper;
	
	/**
	 * 增加模块
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	public Map<String,Object> add${className}(Map<String,Object> entity) {
		int count = ${feildName}Mapper.insert(entity);
		if(count==1) {
			System.out.println(entity+"--");
			return entity;
		}else {
			throw new RuntimeException();
		}
		//return null;
		
	}

	@Override
	public boolean delete${className}ById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

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
