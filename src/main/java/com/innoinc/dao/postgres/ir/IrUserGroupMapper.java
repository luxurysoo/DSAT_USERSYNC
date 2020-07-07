/**
 * IrUserGroup DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;

import com.innoinc.model.postgres.ir.IrUserGroup;




public interface IrUserGroupMapper {
	
	List<IrUserGroup> selectAll();
	
	void insertGroup(IrUserGroup irUserGroup);
	
	void insertGroupList(List<IrUserGroup> irUserGroupList);
    
}
