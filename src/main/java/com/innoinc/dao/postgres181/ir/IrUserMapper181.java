/**
 * IrUser DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres181.ir;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.innoinc.model.postgres181.ir.IrUser181;
import com.innoinc.model.postgres181.ir.IrUserGroup;



public interface IrUserMapper181 {
	@Select("SELECT count(*) FROM ir_user")
	public String getCount(); 
	
	@Select("SELECT version()")
	public String getVersion();
	
    List<IrUser181> selectAll();
    
    int selectIruserByUserid(String Userid);

    List<IrUser181> selectIruserWhere();
    
    void insertUserBean(IrUser181 iruser);
    
    void updateUserBean(IrUser181 iruser);
    
    void insertUserList(List<IrUser181> irUserList);
    
    void deleteIrUser();
}

