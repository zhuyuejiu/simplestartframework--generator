package org.ranger.service.impl;

import java.util.List;
import java.util.Map;

import org.simplestartframwork.context.annotation.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gb.mapper.ModularMapper;
import com.gb.service.ModularService;

@Service
public class ResultServiceImpl implements ModularService{
	
	@Inject
	private ResultMapper resultMapper;
	
	/**
	 * 增加模块
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	public Map<String,Object> addResult(Map<String,Object> entity) {
		int count = resultMapper.insert(entity);
		if(count==1) {
			System.out.println(entity+"--");
			return entity;
		}else {
			throw new RuntimeException();
		}
		//return null;
		
	}

	@Override
	public boolean deleteResultById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> updateResult(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findResultById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findResultByIds(Long ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findResultByEntityLikePage(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
