/**
 * IrUser DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres181.ir;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.innoinc.model.postgres181.ir.IrPhone;
import com.innoinc.model.postgres181.ir.IrUser181;
import com.innoinc.model.postgres181.ir.IrUserGroup;



public interface IrPhoneMapper {
	@Select("SELECT count(*) FROM ir_phone")
	public String getCount(); 
	
	@Select("SELECT version()")
	public String getVersion();
	
    List<IrPhone> selectAll();
    
    int selectIrPhoneByPhoneNo(String phone_no);
 
    void updatePhoneBean(IrPhone ir_phone);
     
}

