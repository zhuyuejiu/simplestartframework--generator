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
public class StudentTeacherServiceImpl implements ModularService{
	
	@Inject
	private StudentTeacherMapper studentTeacherMapper;
	
	/**
	 * 增加模块
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	public Map<String,Object> addStudentTeacher(Map<String,Object> entity) {
		int count = studentTeacherMapper.insert(entity);
		if(count==1) {
			System.out.println(entity+"--");
			return entity;
		}else {
			throw new RuntimeException();
		}
		//return null;
		
	}

	@Override
	public boolean deleteStudentTeacherById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> updateStudentTeacher(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findStudentTeacherById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findStudentTeacherByIds(Long ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findStudentTeacherByEntityLikePage(Map<String, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
