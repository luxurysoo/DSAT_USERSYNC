package com.innoinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.dao.postgres.ir.IrTmpDaishinMapper;
import com.innoinc.dao.postgres.ir.IrUserGroupMapper;
import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrDaishin;
import com.innoinc.model.postgres.ir.IrUserGroup;

@Service
@Transactional
public class IrTmpDaishinService {
	@Autowired
	IrTmpDaishinMapper irTmpDaishinMapper;

	
	public void addGroupList(List<Daishin> daishinList) {
		irTmpDaishinMapper.insertDaishinGroupList(daishinList);
		
	}

	public void deleteTmpDaishin() {
		irTmpDaishinMapper.deleteTmpDaishin();
		
	}
	
	
	public List<IrDaishin> selectTmpViewGroup() {
		List<IrDaishin> groupList = irTmpDaishinMapper.selectTmpViewGroup();
		return groupList;
	}
	
	public String getUpperDept(String deptcode) {
		
		String upperDept =  irTmpDaishinMapper.getUpperDept(deptcode);
		
		return upperDept;
	}

	
 
}
