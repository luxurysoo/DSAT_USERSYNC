/**
 * IrUserAuthInfo DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;
 
import com.innoinc.model.postgres.ir.IrUserAuthInfo;


 

public interface IrUserAuthInfoMapper {
 
	
	int selectIruserAuthByUserid(String Userid);
	
 
 
    void updateUserAuthInfoBean(IrUserAuthInfo ir_user_auth_info);
    
    void insertUserAuthInfoBean(IrUserAuthInfo ir_user_auth_info);
    
 
}

