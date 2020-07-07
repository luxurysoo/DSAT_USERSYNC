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
	private String user_id;
	
	private String user_name;
		
	private String passwd;

	private String group_path;
	
	private String auth_level;
	
	private Date reg_date;
	
	private Date last_call_time;
	
	private Date login_time;
	
	private Date logoff_time;

	private String login_lock_reason;
}
