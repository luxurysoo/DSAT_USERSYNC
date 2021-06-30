/**
 * Ir_user table Model
 * 
 * 테이블 속성을 매핑
 * 20201120  job_auth_code 추가함 (compliance='Y' 는 모두 검색가능해야 함. 추가요청 ) 
 * @author  
 */

package com.innoinc.model.postgres181.ir;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("iruser181")
public class IrUser181 
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
	private String group_path;
	private String group_id;
	private String user_id;
	private String user_name;
	private String retire_yn;
	private String passwd;
	private int auth_level;
	private String last_name;
	private String first_name;
	private String user_pass_sso;
	private int job_auth_code; 
	
}
