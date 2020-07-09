/**
 * Ir_user table Model
 * 
 * 테이블 속성을 매핑
 * 
 * @author 박민성
 */

package com.innoinc.model.postgres.ir;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("iruser")
public class IrUser 
{
	private String emp_no;
	private String emp_name;
	private String dept_code;
	private String dept_name;
	private String upper_dept;
	private String upper_name;
	private String office_phone;
	private String title_code;
	private String compliance;
	
	
}
