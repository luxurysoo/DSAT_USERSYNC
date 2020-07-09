package com.innoinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrDaishin;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;

@Service
public class SyncService {
	   @Autowired
	   private DaeshinService daeshinservice;
	   @Autowired
	   private IrUserService irUserService;
	   @Autowired
	   private IrUserGroupService irUserGroupService;
	   @Autowired
	   private IrTmpDaishinService irTmpDaishinService;
	   
	   List<Daishin> daeshinList;
	   Daishin daishin;
	   List<IrUser> irUserList;
	   IrUserGroup irUserGroup;
	   List<IrUserGroup> irUserGroupList;
	   List<IrDaishin> dsGroupList;
	   
	   public void daeshinPrint() {
		  	 daeshinList = daeshinservice.selectAll();
		  	// daeshinList.forEach(daishin -> {
		  	//	 System.out.println(daishin.toString());
		  	// });
		  	 
		  //	 System.out.println(irUserService.selectVersion());
	   }
	   
	   public void addUserGroup() {

		  	 
		  	 //irUserGroup = new IrUserGroup();
		  	 //irUserGroup.setGroup_id("00");
		  	 //irUserGroup.setGroup_depth(1);
		  	 //irUserGroup.setGroup_name("蹂댁긽");
		  	 //irUserGroup.setGroup_path("ROOT00");
		  	 //irUserGroup.setGroup_display_name("蹂댁긽");
		  	 
		   	irTmpDaishinService.addGroupList(daeshinList);
		  	 
		   	 
		  	// irUserGroupList = irUserGroupService.selectAll();
		  	// irUserGroupList.forEach(i -> {
		  	//	 System.out.println(i.toString());
		  	// });
		   	
		   	
	   }
	   
	   
	   
	   public void addUser() {

		  	 
		  	 //irUserGroup = new IrUserGroup();
		  	 //irUserGroup.setGroup_id("00");
		  	 //irUserGroup.setGroup_depth(1);
		  	 //irUserGroup.setGroup_name("蹂댁긽");
		  	 //irUserGroup.setGroup_path("ROOT00");
		  	 //irUserGroup.setGroup_display_name("蹂댁긽");
		  	 
		   	irUserService.addUserList(irUserList);
		  	 
		   	 
		  	// irUserGroupList = irUserGroupService.selectAll();
		  	// irUserGroupList.forEach(i -> {
		  	//	 System.out.println(i.toString());
		  	// });
		   	
		   	
	   }
	   
	   
	   public void getTmpGroup() {
		   dsGroupList = irTmpDaishinService.selectTmpViewGroup();
		   dsGroupList.forEach(dsGroupList -> {
			  // System.out.println("tmpgroup :  " + dsGroupList.toString());
		   });
		  	 
		//   System.out.println(IrTmpDaishinService.selectVersion());
	   }
	   
	   public void removeTmpDaishin() {
		   irTmpDaishinService.deleteTmpDaishin();
	   }
	   
	   
	   public void removeIrUserGroup() {
		   irUserGroupService.removeIrUserGroup();
	   }
	   
	   
	   
	   public void insertUserGroup() {
		   irUserGroup = new IrUserGroup();
		   
		   // ROOT 대신자산신탁 수동입력.
		   irUserGroup.setGroup_id("SYSY");
		   irUserGroup.setGroup_name("대신자산신탁");
		   irUserGroup.setGroup_path("ROOT");
		   irUserGroup.setGroup_display_name("대신자산신탁");
		   irUserGroup.setGroup_desc("대신자산신탁");
		   irUserGroup.setDept_code("00010");
		   irUserGroup.setDept_name("대신자산신탁");
		   irUserGroup.setUpper_dept("");
		   irUserGroup.setUpper_name("대신자산신탁");
		   irUserGroup.setGroup_depth(0);
		   irUserGroupService.addGroup(irUserGroup);
		   
		   
		   dsGroupList.forEach(dsGroupList -> {
			   String group_id = "";
			   String group_name = "";
			   String group_path = "";
			   String group_display_name = "";
			   String dept_code ="";
			   String dept_name ="";
			   String upper_dept ="";
			   String upper_name ="";
			   int group_depth=0;
			   String group_parent_id="";
			   
			 
			    
			   
			   
			   group_id= dsGroupList.getDept_code();
			   group_name=dsGroupList.getDept_name();
			   group_display_name= group_name;
			   dept_code = dsGroupList.getDept_code();
			   dept_name = dsGroupList.getDept_name();
			   upper_dept = dsGroupList.getUpper_dept();
			   upper_name = dsGroupList.getUpper_name();
			   group_parent_id =upper_dept.substring(1,5);
			   group_id = group_id.substring(1,5);
		 
			   
			   
			   String strPath = "";
			   String selectCode = dept_code;
			//   System.out.println("		조회시작 selectCode : " + selectCode);
			   int i = 0;
			   int depth = 0;
			   if (!selectCode.equals("00010")){
				   while ( i<5) {
					   
					   String tmpcode = "";
					   //System.out.println("*** group_depth 1 : " + i);
				//	   System.out.println("재귀 selectCode : " + selectCode);
					   tmpcode = irTmpDaishinService.getUpperDept(selectCode);
					   i++;
					   depth=i;
					   if (! tmpcode.equals(selectCode)) {
						//   System.out.println("** selectcode  : " + selectCode);
						//   System.out.println("** get upperdept : " + tmpcode);
						   selectCode = tmpcode;
						   if ( tmpcode.equals("00010")) {
							   strPath = strPath ;
						   }else {
							   strPath = tmpcode.substring(1,5)+ strPath ;
						   }
						//   System.out.println("grouppath : " + strPath);
						//   System.out.println("tmpcode : " + tmpcode);
						   if (tmpcode.equals("00010")) {
								break;
						   }
					   }else {
						 //  System.out.println(" dept와 uppper가 동일함 패스 " + tmpcode + "/"+ selectCode);
						   break;
					   }
					   
					   
					    
				   }
				   
				   
				    
			   }  
			   System.out.println("*** 					group_depth  : " + depth);
			   group_path="ROOT"+strPath +group_id;   
			 
			   
			   
		//	   System.out.println("***** group_path : " + group_path);
			   if(upper_dept.equals("00010")) {
				   group_depth=1;
				   group_path = "ROOT"  + group_id;
				   group_parent_id ="SYST";
			   }
		//	   System.out.println("group_path2 : " + group_path);
			   if(dept_code.equals("00010")) {
				   group_depth=0;
				   group_parent_id ="";
			   }
			   group_depth=depth;
			   System.out.println("group_id : " + group_id);
			   System.out.println("group_path : " + group_path);
			   System.out.println("group_parent_id : " + group_parent_id);
			   irUserGroup.setGroup_id(group_id);
			   irUserGroup.setGroup_name(group_name);
			   irUserGroup.setGroup_path(group_path);
			   irUserGroup.setGroup_display_name(group_display_name);
			   irUserGroup.setGroup_desc(group_name);
			   irUserGroup.setDept_code(dept_code);
			   irUserGroup.setDept_name(dept_name);
			   irUserGroup.setUpper_dept(upper_dept);
			   irUserGroup.setUpper_name(upper_name);
			   irUserGroup.setGroup_depth(group_depth);
			   irUserGroup.setGroup_parent_id(group_parent_id);
			   // insert ir_user_group
			   //if(!dept_code.equals(upper_dept)) {
			   
			   //System.out.println( "%#$@% dept_code : " +  dept_code );
			   String code = irUserGroupService.selectGroupByCd(dept_code);
			   //System.out.println( "code 0 or 1 : " + code );
			   if ( code.equals("0") ) {
				   irUserGroupService.addGroup(irUserGroup);
			   }
			   //}
		   });
		   
	   }
	   
	   
}
