package com.innoinc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrDaishin;
import com.innoinc.model.postgres.ir.IrPhone;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserAuthInfo;
import com.innoinc.model.postgres.ir.IrUserGroup;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


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
	   @Autowired
	   private IrUserAuthInfoService irUserAuthInfoService;
	   @Value("${init_password}") 
	   private String init_password;
	   
	   private final Logger logger = LoggerFactory.getLogger(this.getClass());

	   
	   
	   List<Daishin> daeshinList;
	   Daishin daishin;
	   List<IrUser> irUserList;
	   IrUserGroup irUserGroup;
	   List<IrUserGroup> irUserGroupList;
	   List<IrDaishin> dsGroupList;
	   IrUser iruser;
	   List<IrDaishin> irUserDSList;
	   IrPhone ir_phone;
	   IrUserAuthInfo ir_user_auth_info;
	   
	   
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
		   try {
			   daeshinList = daeshinservice.selectAll();
		  	// daeshinList.forEach(daishin -> {
		  	//	 System.out.println(daishin.toString());
		  	// });
			   logger.info("oracle 에서 정보 가져오기 " );
		  //	 System.out.println(irUserService.selectVersion());
		   }catch (Exception e ) {
			   logger.error("oracle 에서 정보 가져오기 오류 " + e.getMessage() );
		   }
	   }
	   
	   public void addUserGroup() {
		   try {
			   irTmpDaishinService.addGroupList(daeshinList);
			   logger.info("tmp_daishin 테이블에 데이터 입력 (postgres) " );
		   }catch (Exception e ) {
			   logger.error("tmp_daishin 테이블에 데이터 입력 (postgres) 오류 " + e.getMessage() );
		   }
	   }
	   
	   
	   
	   public void addUser() {
		   // 여기서만 초기화 해준다. 
		   logger.info("user 정보 입력  " );
		   IrPhone ir_phone = new IrPhone();
		   IrUserAuthInfo ir_user_auth_info = new IrUserAuthInfo();
		   
		   irUserList.forEach(iruser -> {
			   //System.out.println("DDDDDDDDDDDDDDDDDD :"+iruser.toString());
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
			   // 부서장 여부  Y/N
			   String title_code = iruser.getTitle_code();
			   // complience 여부 Y/N
			   String compliance = iruser.getCompliance();
			   System.out.println("compliance : " +		user_id + "/ " +iruser.getCompliance()		   );
			  /* 
			   System.out.println("retire_YN" +		iruser.getRetire_yn()		   );
			   
			   System.out.println("phone_no" +		phone_no		   );
			   System.out.println("group_path" +	group_path		   );
			   System.out.println("user_id" +		user_id		);
			   System.out.println("user_name" +	user_name		);
			   System.out.println("user_group_name" +  user_group_name   );
			   
			   System.out.println(ir_phone.toString());
			   */ 
			   // ir_phone bean 에 데이터 설정
			   ir_phone.setGroup_path(group_path);
			   ir_phone.setUser_group_path(group_path);
			   ir_phone.setUser_group_name(user_group_name);
			   ir_phone.setUser_id(user_id);
			   ir_phone.setUser_name(user_name);
			   
			   // ir_user_auth_info  에 데이터 설정
			   
			   ir_user_auth_info.setUser_id(user_id);
			   int  tmp_auth_code = 0;
			   
			   if (title_code.equals("Y")) {
				   tmp_auth_code=11;
				   iruser.setAuth_level(7);
				   iruser.setJob_auth_code(11);		// job auth code 추가함.  
				 
			   }
			   
			   if (compliance.equals("Y") ) {
				   iruser.setAuth_level(9);
				   iruser.setJob_auth_code(30);	// job auth code 추가함.  30은 모든 조회가능함
			   }
			   
			   ir_user_auth_info.setJob_auth_code(tmp_auth_code);
			   ir_user_auth_info.setGroup_path(group_path);
			   // ir_user_auth_info 데이터 설정 끝
			   
			   
			   boolean bret_phone_no=false;
			   int cnt = phone_no.length() ; 
			   
			   if (cnt >= 4 ) {
				   	System.out.println("phonelastindex "+phone_no.substring(cnt-4,cnt) );
				   	String tmpno = phone_no.substring(cnt-4,cnt);
				   	iruser.setOffice_phone(tmpno);
				   	ir_phone.setPhone_status(0);	//20200806 phone_status 0으로 수정함 1은 채널장애
				   	bret_phone_no=true;
				   	ir_phone.setPhone_no(tmpno);
		   		} 
			   
			   String retire = iruser.getRetire_yn()	;
			   System.out.println("retire  : "+retire);
			   if (retire.equals("N") ) {
				   // 퇴직자 state 1 처리
				   ir_phone.setUser_state(1);
				   ir_phone.setUser_status(1);
			   }else {
				   // 퇴직그룹으로 변경 
				   ir_phone.setUser_id("");
				   ir_phone.setUser_group_name(root_group_name);	// x퇴직자 추가처리
				   ir_phone.setGroup_path(root_group_path);			// x퇴직자 추가처리
				   ir_phone.setUser_group_name(root_group_name);	// x퇴직자 추가처리
				   ir_phone.setUser_name("");						// x퇴직자 추가처리
				   ir_phone.setUser_state(0);		
				   ir_phone.setUser_group_path(root_group_path);	// 퇴직자는 루트그룹으로 변경
				   ir_phone.setGroup_path(root_group_path);			// 퇴직자는 루트그룹으로 변경	
				   iruser.setGroup_path(retire_group_path);
				   iruser.setGroup_id(retire_group_id);
				   ir_phone.setUser_status(0);
			   }
			   
			   // 패스워드 연동 추가 20201026
			   String user_pass_sso = iruser.getUser_pass_sso();
			   iruser.setPasswd(user_pass_sso);
			   // 패스워드 연동 추가 20201026
			   
			   // last name / first name 추가 20201026
			   String nm_last ="";
			   String nm_first = "";
			   if (user_name.length()>=2) {
			   nm_last = user_name.substring(0,1);
			   nm_first = user_name.substring(1,user_name.length());
			   
			   logger.info("last name : " + nm_last );
			   logger.info("first name : " + nm_first );
			   
			   }
			   iruser.setLast_name(nm_last);
			   iruser.setFirst_name(nm_first);
			   int dubchk = -1;
			   dubchk=irUserService.selectUser(iruser.getEmp_no());
			   
			   

			   			   
			   if (dubchk ==1 ) {
				   try {
					   logger.debug("데이터 중복되어 update 처리 : " +iruser.getEmp_no());
					   logger.debug("update 패스워드 : " +iruser.getUser_pass_sso());
					  // System.out.println("데이터 중복되어 update 처리 : "+iruser.getEmp_no());
					   irUserService.updateUserBean(iruser);
					   logger.debug("데이터 중복되어 update 처리 : "+ir_user_auth_info.getUser_id());
				   }catch (Exception e ) {
					   logger.error("데이터 중복되어 update 처리오류  : "+ir_user_auth_info.getUser_id() +  " / " + e.getMessage());
				   }
				   
			   }else {
				   try {
					   logger.debug("데이터 insert 처리 : " +iruser.getEmp_no() );
					   //logger.debug("패스워드 초기화 처리함 : " + init_password );
					   //System.out.println("데이터 insert 처리 : "+iruser.getEmp_no());
					   // 신규등록시는 초기화 패스워드 처리함. 업데이트시에는 password 수정안함.
					   // 패스워드 초기화는 user_id + inno + init_password 의 str을 sha256 hash함.
					   //String enc_initpasswd = getEncrypt(user_id+"inno"+init_password);
					   //logger.debug("암호화된 초기화 패스워드"+ enc_initpasswd);
					   //iruser.setPasswd(enc_initpasswd);
					   // 패스워드 초기화 값을 동기화한 패스워드로 변경함. 20201026
					   iruser.setPasswd(user_pass_sso);	 // user_pass_sso 로 변경함. 
					   irUserService.addUserBean(iruser );
				   }catch (Exception e ) {
					   logger.debug("데이터 insert 처리 오류 : " +iruser.getEmp_no() + " / " + e.getMessage() );
				   }
			   }
			   
			   // user_auth_info 정보 입력 또는 업데이트 
			   
			   //조회먼저한다.
			   int dubchk2 = -1;
			   System.out.println("ir_user_auth_info.getUser_id() " + ir_user_auth_info.getUser_id());
			   
			   dubchk2 = irUserAuthInfoService.selectUserAuth(ir_user_auth_info.getUser_id());
			   if (dubchk2 ==1 ) {
				//   System.out.println("ir_user_auth_info 데이터 중복되어 update 처리 : "+ir_user_auth_info.getUser_id());
				   logger.debug("ir_user_auth_info 데이터 중복되어 update 처리 : "+ir_user_auth_info.getUser_id());
				   irUserAuthInfoService.updateUserAuthBean(ir_user_auth_info);
			   }else {
				   logger.debug("ir_user_auth_info 데이터 insert 처리 : "+iruser.getEmp_no());
				 //  System.out.println("ir_user_auth_info 데이터 insert 처리 : "+iruser.getEmp_no());
				   irUserAuthInfoService.addUserAuthBean(ir_user_auth_info );
			   }
			   
			  // System.out.println("init_password  : "+init_password);
			   
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
				   /*
				   System.out.println("폰정보 업데이트   : "+ bret_phone_no);
				   System.out.println("폰정보 업데이트 phone_no   : "+ ir_phone.getPhone_no());
				   System.out.println("폰정보 업데이트 User_status   : "+ ir_phone.getUser_state());
			    	*/
				   irPhoneService.updatePhoneBean(ir_phone);
			   }
			   
			   
		   });
		   logger.info("폰정보 업데이트  " );	
	   }
	   
	   @SuppressWarnings("finally")
	public int selectUserById(String id) {
		   int cnt = -1;
		   try {
			   cnt=irUserService.selectUser(id);
			
			   logger.info("ir_user 조회   " + id  );
			   
		   }catch (Exception e ) {
			   logger.error("ir_user 조회   " + id  );
		   }finally {
			   return cnt;
		   }
		   
	   }
	   
	   
	   
	   public void getTmpGroup() {
		   
		   try {
			   dsGroupList = irTmpDaishinService.selectTmpViewGroup();
			   dsGroupList.forEach(dsGroupList -> {
				  // System.out.println("tmpgroup :  " + dsGroupList.toString());
			   });
			//   System.out.println(IrTmpDaishinService.selectVersion());
			   logger.info("tmp_daishin의 데이터로 그룹정보 조회 (view table)" );
		   }catch (Exception e ) {
			   logger.error("tmp_daishin의 데이터로 그룹정보 조회 (view table)" + e.getMessage() );
		   }
	   }
	   
	   public void removeTmpDaishin() {
		   try {
			   irTmpDaishinService.deleteTmpDaishin();
			   logger.info("tmp_daishin 테이블 삭제 " );
		   }catch (Exception e ) {
			   logger.error("tmp_daishin 테이블 삭제 " + e.getMessage());
		   }
	   }
	   
	   
	   public void removeIrUserGroup() {
		   irUserGroupService.removeIrUserGroup();
		   logger.info("ir_user_group 삭제 " );
		   
	   }
	   
	   public void removeIrUser() {
		   irUserService.removeIrUser();
	   }
	   
	   
	   public void insertUserGroup() {
		   irUserGroup = new IrUserGroup();
		   
		   try {
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
		   irUserGroup.setUpper_dept("SYST");		// "" -> SYST로 변경 20200730
		   irUserGroup.setDept_name(retire_group_name);
		   //irUserGroup.setUpper_dept("");
		   irUserGroup.setUpper_name(root_group_name);
		   irUserGroup.setGroup_depth(1);
		   irUserGroup.setGroup_parent_id("SYST");		//20201120 추가
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
			   String selectCode = dept_code.trim();		// trim 추가함.20201120
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
			 
			   //group_depth=depth;
			   
		//	   System.out.println("***** group_path : " + group_path);
			   // 20201120 parent path 가 이상 발생하여 추가함.  
			   if (!dept_code.equals("00010")) {
			   String tmpcode2 = irTmpDaishinService.getUpperDept(dept_code);
			   //logger.info("dept_code :  " +dept_code );
			   //logger.info("parent_path :" +tmpcode2 );
				   if(tmpcode2.equals("00010")) {
					   group_depth=1;
					   group_path = "ROOT"  + group_id;
					   group_parent_id ="SYST";
				   }
			   }
			   
			   // 20201120 추가 끝
			   if(upper_dept.equals("00010")) {
				   group_depth=1;
				   group_path = "ROOT"  + group_id;
				   group_parent_id ="SYST";
				   
			   }
			   // 추가함. group_depth=1 인경우 group_parent_id ="SYST"; 로 추가 설정
			   if(group_depth==1) {
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
			   System.out.println("group_depth : " + group_depth);
			   
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
		   logger.info("ir_user_group 입력  " );
		   }catch (Exception e ) {
			   logger.info("ir_user_group 입력 오류 " + e.getMessage() );
		   }
	   }
	   
	   // ir_user_auth_info 조회. 부서장 정보 업데이트 처리 .
	   @SuppressWarnings("finally")
	public int getUserAuthInfo(String id) {
		   int cnt = -1;
	   
		   try {
			   cnt = irUserAuthInfoService.selectUserAuth(id);
			   logger.info("ir_user 정보 입력 " );
		   }catch (Exception e ) {
			   logger.info("ir_user 정보 입력오류 " + e.getMessage() );
		   }finally {
			   return cnt;
		   }
	   }
	   
	   
	   
	   public void getUserList() { 
		   try {
			   irUserList = irTmpDaishinService.selectUserInfo();
			   logger.info("ir_user 정보 입력 " );
		   }catch (Exception e ) {
		   //System.out.println("DREFEFEFEF : "+irUserList);
			   logger.error("ir_user 정보 입력 " + e.getMessage());
		   }
	   }
	   
 
		
		/**
		 * SHA-256 암호화 함
		 * @param source 원본
		 * @return
		 */
		public static String getEncrypt(String source) {
			
			String result = "";
			
			byte[] a = source.getBytes();
			byte[] bytes = new byte[a.length];
			
			System.arraycopy(a, 0, bytes, 0, a.length);
			//System.arraycopy(salt, 0, bytes, a.length, salt.length);
			
			try {
				// 암호화 방식 지정 메소드
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(bytes);
				
				byte[] byteData = md.digest();
				
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
				}
				
				result = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		 
		
		
	  
}
