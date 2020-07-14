/**
 * IrUser DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;



public interface IrUserMapper {
	@Select("SELECT count(*) FROM ir_user")
	public String getCount(); 
	
	@Select("SELECT version()")
	public String getVersion();
	
    List<IrUser> selectAll();
    
    int selectIruserByUserid(String Userid);

    List<IrUser> selectIruserWhere();
    
    void insertUserBean(IrUser iruser);
    
    void updateUserBean(IrUser iruser);
    
    void insertUserList(List<IrUser> irUserList);
    
    void deleteIrUser();
}

