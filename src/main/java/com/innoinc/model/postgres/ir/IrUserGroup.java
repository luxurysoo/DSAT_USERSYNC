package com.innoinc.model.postgres.ir;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ir_user_group")
public class IrUserGroup {
	private String group_id;
	private String group_path;
	private int group_depth;
	private String group_name;
	private String group_display_name;
}
