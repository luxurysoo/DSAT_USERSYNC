package com.innoinc.model.postgres.ir;

 

import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("ir_user_auth_info")
public class IrUserAuthInfo {
	private String user_id;
	private String group_path;
	private int job_auth_code;
	private int two_phsition;
	private String from_date;
	private String to_date;
	
}
 