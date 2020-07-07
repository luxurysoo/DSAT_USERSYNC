/**
 * Daishin table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.oracle.daishin.DaishinMapper;
import com.innoinc.model.oracle.daishin.Daishin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional		// 오류발생시 rollback 설정
public class DaeshinService{
	@Autowired    
	  DaishinMapper daishinMapper;
	
	public List<Daishin> selectAll() {
		List<Daishin> daishinList = daishinMapper.selectAll();
		return daishinList;
	}
	
	public String selectVersion() {
		String value = daishinMapper.getVersion();
		return value;
	}
	
	public Daishin selectDaishinByCd(String group_code) {
		
		Daishin daishin =  daishinMapper.selectDaishinByCd(group_code);
		
		return daishin;
	}
}
