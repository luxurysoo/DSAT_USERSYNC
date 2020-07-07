package com.innoinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.dao.postgres.ir.IrUserGroupMapper;
import com.innoinc.model.postgres.ir.IrUserGroup;

@Service
@Transactional
public class IrUserGroupService {
	@Autowired
	IrUserGroupMapper irUserGroupMapper;
	
	public List<IrUserGroup> selectAll() {
		List<IrUserGroup> irUserGroupList = irUserGroupMapper.selectAll();
		return irUserGroupList;
	}
	
	public void addGroup(IrUserGroup irUserGroup) {
		irUserGroupMapper.insertGroup(irUserGroup);
	}
	
	public void addGroupList(List<IrUserGroup> groupList) {
		irUserGroupMapper.insertGroupList(groupList);
		
	}
}
