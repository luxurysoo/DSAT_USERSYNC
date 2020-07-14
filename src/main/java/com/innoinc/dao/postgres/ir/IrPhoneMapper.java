/**
 * IrUser DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.innoinc.model.postgres.ir.IrPhone;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;



public interface IrPhoneMapper {
	@Select("SELECT count(*) FROM ir_phone")
	public String getCount(); 
	
	@Select("SELECT version()")
	public String getVersion();
	
    List<IrPhone> selectAll();
    
    int selectIrPhoneByPhoneNo(String phone_no);
 
    void updatePhoneBean(IrPhone ir_phone);
     
}

