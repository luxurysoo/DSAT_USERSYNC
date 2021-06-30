/**
 * Ir_Phone table Model
 * 
 * 테이블 속성을 매핑
 * 
 * @author  
 */

package com.innoinc.model.postgres181.ir;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ir_phone")
public class IrPhone 
{
	private String phone_no;
	private String phone_id;
	private String phone_mac;
	private String phone_ip;
	private String group_path;
	private String user_id;
	private String user_ip;
	private int user_state;
	private String user_name;
	private String user_group_path;
	private String user_group_name;
	private int phone_status;
	private int user_status;		//20200730 추가

}
