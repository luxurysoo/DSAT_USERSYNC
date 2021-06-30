/**
 * IrUserGroup DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres181.ir;

import java.util.List;

import com.innoinc.model.postgres181.ir.IrUserGroup;




public interface IrUserGroupMapper {
	
	List<IrUserGroup> selectAll();
	
	void insertGroup(IrUserGroup irUserGroup);
	
	void insertGroupList(List<IrUserGroup> irUserGroupList);
	void deleteIrUserGroup();
	public String selectGroupByCd(String deptcode);
	
	 
}
