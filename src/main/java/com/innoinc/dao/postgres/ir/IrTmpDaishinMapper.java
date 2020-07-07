/**
 * IrUserGroup DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;

import com.innoinc.model.oracle.daishin.Daishin;





public interface IrTmpDaishinMapper {
	
	void insertDaishinGroupList(List<Daishin> daishinList);
    
}
