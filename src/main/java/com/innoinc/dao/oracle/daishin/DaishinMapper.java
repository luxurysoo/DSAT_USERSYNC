/**
 * Daishin DAO
 * 
 * @author ???
 */

package com.innoinc.dao.oracle.daishin;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.innoinc.model.oracle.daishin.Daishin;



public interface DaishinMapper {
	@Select("SELECT * FROM daeshin")
	public String getCount(); 
	
	
	@Select("SELECT * FROM v$version WHERE banner LIKE 'Oracle%'")
	public String getVersion();
	
    List<Daishin> selectAll();
    
    Daishin selectDaishinByCd(String dept_code);

 //   List<Daishin> selectIruserWhere();
    
}
