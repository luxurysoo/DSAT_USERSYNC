/**
 * IrUserGroup DAO
 * 
 * @author ???
 */

package com.innoinc.dao.postgres.ir;

import java.util.List;

import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrDaishin;
import com.innoinc.model.postgres.ir.IrUserGroup;





public interface IrTmpDaishinMapper {
	
	void insertDaishinGroupList(List<Daishin> daishinList);
    
	void deleteTmpDaishin();
    
	public String getUpperDept(String deptcode);
	
	List<IrDaishin> selectTmpViewGroup();
	
}
