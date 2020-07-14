package com.innoinc.model.postgres.ir;

 

import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("ir_daishin")
public class IrDaishin {
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
}
