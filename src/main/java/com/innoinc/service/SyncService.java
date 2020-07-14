package com.innoinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrDaishin;
import com.innoinc.model.postgres.ir.IrPhone;
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
	   @Autowired
	   private IrPhoneService irPhoneService;
	   
	   List<Daishin> daeshinList;
	   Daishin daishin;
	   List<IrUser> irUserList;
	   IrUserGroup irUserGroup;
	   List<IrUserGroup> irUserGroupList;
	   List<IrDaishin> dsGroupList;
	   IrUser iruser;
	   List<IrDaishin> irUserDSList;
	   IrPhone ir_phone;
	   // 퇴사자 그룹id.
	   String retire_group_id="ZZZZ";
	   // 퇴사자 그룹명.
	   String retire_group_name="퇴사자"; 
	   // 퇴사자 그룹패스.
	   String retire_group_path="ROOTZZZZ"; 
	   
	   // ROOT 그룹id.
	   String root_group_id="SYST";
	   // ROOT 그룹명.
	   String root_group_name="대신자산신탁";	   
	   // ROOT 그룹패스.
	   String root_group_path="ROOT";
	   
	   
	   public void daeshinPrint() {
		  	 daeshinList = daeshinservice.selectAll();
		  	// daeshinList.forEach(daishin -> {
		  	//	 System.out.println(daishin.toString());
		  	// });
		  	 
		  //	 System.out.println(irUserService.selectVersion());
	   }
	   
	   public void addUserGroup() {
		   	irTmpDaishinService.addGroupList(daeshinList);
	   }
	   
	   
	   
	   public void addUser() {
		   // 여기서만 초기화 해준다. 
		   IrPhone ir_phone = new IrPhone();
		   
		   
		   irUserList.forEach(iruser -> {
			   System.out.println("DDDDDDDDDDDDDDDDDD :"+iruser.toString());
			   iruser.getEmp_no();
			  // System.out.println(irUserList.getEmp_no());
		//	   String user_id = irUserList.getEmp_no();
		//	   iruser.setEmp_no(user_id); 
			 //  System.out.println("USERID : " + iruser.getEmp_no());
			   //iruser.setEmp_no(irUserList.getEmp_no());
			   //System.out.println("user_id : " + iruser.getEmp_no());
			   String phone_no = iruser.getOffice_phone();
			   String group_path = iruser.getGroup_path();
			   String user_id=iruser.getEmp_no();
			   String user_name=iruser.getEmp_name();
			   String user_group_name = iruser.getDept_name();
			   System.out.println("retire_YN" +		iruser.getRetire_yn()		   );
			   
			   System.out.println("phone_no" +		phone_no		   );
			   System.out.println("group_path" +	group_path		   );
			   System.out.println("user_id" +		user_id		);
			   System.out.println("user_name" +	user_name		);
			   System.out.println("user_group_name" +  user_group_name   );
			   
			   System.out.println(ir_phone.toString()); 
			   // ir_phone bean 에 데이터 설정
			   
			   ir_phone.setGroup_path(group_path);
			   ir_phone.setUser_group_path(group_path);
			   ir_phone.setUser_group_name(user_group_name);
			   ir_phone.setUser_id(user_id);

			   
			   boolean bret_phone_no=false;
			   int cnt = phone_no.length() ; 
			   
			   if (cnt >= 4 ) {
				   	System.out.println("phonelastindex "+phone_no.substring(cnt-4,cnt) );
				   	String tmpno = phone_no.substring(cnt-4,cnt);
				   	iruser.setOffice_phone(tmpno);
				   	ir_phone.setPhone_status(1);
				   	bret_phone_no=true;
				   	ir_phone.setPhone_no(tmpno);
		   		} 
			   
			   String retire = iruser.getRetire_yn()	;
			   System.out.println("retire  : "+retire);
			   if (retire.equals("N") ) {
				   ir_phone.setUser_state(1);
			   }else {
				   ir_phone.setUser_state(0);
				   ir_phone.setUser_group_path(retire_group_path);
				   iruser.setGroup_path(retire_group_path);
				   iruser.setGroup_id(retire_group_id);
			   }
			   
			   
				   
			   int dubchk = -1;
			   dubchk=irUserService.selectUser(iruser.getEmp_no());
			   
			   if (dubchk ==1 ) {
				   System.out.println("데이터 중복되어 update 처리 : "+iruser.getEmp_no());
				   irUserService.updateUserBean(iruser);
			   }else {
				   System.out.println("데이터 insert 처리 : "+iruser.getEmp_no());
				   irUserService.addUserBean(iruser );
			   }
			   
			   // 폰정보 업데이트
			   // group_path, user_id, user_status , user_name, user_group_path, user_login_time=now() , user_group_name, user_logoff_time ( 퇴직자로 변경되면 설정 ? ) , user_state= 1 update , 
			   // 1039   1092
			   // 1038   1061
			   
			   
			   // update ir_phone set phone_no='1092' where phone_no='1039';
			   // update ir_phone set phone_no='1061' where phone_no='1038';
			   // update ir_phone set phone_no='1076' where phone_no='1995';
			   // update ir_phone set phone_no='6261' where phone_no='1037';
			   // 폰정보 업데이트  (내선번호가 있을때만 )
			   
			   if (bret_phone_no==true ) {
				   System.out.println("폰정보 업데이트   : "+ bret_phone_no);
				   System.out.println("폰정보 업데이트 phone_no   : "+ ir_phone.getPhone_no());
				   System.out.println("폰정보 업데이트 User_status   : "+ ir_phone.getUser_state());
 
				   irPhoneService.updatePhoneBean(ir_phone);
			   }
			   
			   
		   });
		   	
	   }
	   
	   public int selectUserById(String id) {
		   	int cnt = irUserService.selectUser(id);
		   	return cnt;
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
	   
	   public void removeIrUser() {
		   irUserService.removeIrUser();
	   }
	   
	   
	   public void insertUserGroup() {
		   irUserGroup = new IrUserGroup();
		   
		   // ROOT 대신자산신탁 수동입력.
		   irUserGroup.setGroup_id(root_group_id);
		   irUserGroup.setGroup_name(root_group_name);
		   irUserGroup.setGroup_path(root_group_path);
		   irUserGroup.setGroup_display_name(root_group_name);
		   irUserGroup.setGroup_desc(root_group_name);
		   irUserGroup.setDept_code("00010");
		   irUserGroup.setDept_name(root_group_name);
		   irUserGroup.setUpper_dept("");
		   irUserGroup.setUpper_name(root_group_name);
		   irUserGroup.setGroup_depth(0);
		   irUserGroupService.addGroup(irUserGroup);
		   // 퇴직자 그룹 생성
		   irUserGroup.setGroup_id(retire_group_id);
		   irUserGroup.setGroup_name(retire_group_name);
		   irUserGroup.setGroup_path(retire_group_path);
		   irUserGroup.setGroup_display_name(retire_group_name);
		   irUserGroup.setGroup_desc(retire_group_name);
		   irUserGroup.setDept_code("");
		   irUserGroup.setDept_name(retire_group_name);
		   irUserGroup.setUpper_dept("");
		   irUserGroup.setUpper_name(root_group_name);
		   irUserGroup.setGroup_depth(1);
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
		//	   System.out.println("*** 					group_depth  : " + depth);
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
			   // user입력 위하여 리스트 생성
			   
			   
			   //System.out.println( "%#$@% dept_code : " +  dept_code );
			   String code = irUserGroupService.selectGroupByCd(dept_code);
			   //System.out.println( "code 0 or 1 : " + code );
			   if ( code.equals("0") ) {
				   irUserGroupService.addGroup(irUserGroup);
			   }
			   //}
		   });
		   
	   }
	   
	   public void getUserList() { 
		   irUserList = irTmpDaishinService.selectUserInfo();
		   
		   //System.out.println("DREFEFEFEF : "+irUserList);
	   }
	   public void getIrUserList() {
		//   irUserList = .selectUserInfo();
	   }
}
