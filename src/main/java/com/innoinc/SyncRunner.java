package com.innoinc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.innoinc.service.SyncService;


@SpringBootApplication
public class SyncRunner implements CommandLineRunner{


	@Autowired
	SyncService syncService;

	@Override
	public void run(String... args) throws Exception {
	   // tmp_daishin 테이블 삭제
	   syncService.removeTmpDaishin();
	   // oracle 접속하여 데이터 가져오기
	   syncService.daeshinPrint();
	   // tmp_daishin에 데이터 insert 
	   syncService.addUserGroup();
	   // tmp_daishin 데이터 가져오기.
	   syncService.getTmpGroup();
	   // ir_user_group 데이터 삭제
	   syncService.removeIrUserGroup();
	   // ir_user_group 초기데이터 넣기
	   syncService.insertUserGroup();
	   // ir_user 정보 가져오기
	   syncService.getUserList();
	   // ir_user 삭제하기
	   //syncService.removeIrUser();
	   // ir_user 정보 insert 또는 update 하기 , ir_phone 정보 업데이트	   
	   syncService.addUser(); 
	   
	   // 남은것들
	   // 1. 전화번호는 어떻게 뒤에 4자리만?? -- > 처리함.
	   // 2. 패스워드는 어떻게 백업 후 업데이트??	
	   // 3. ir_phone에 정보 업데이트 해야함. -- 처리함 (확인필요)
	   // 4. 퇴직자 처리 ( 그룹 생성 , 퇴직시 그룹정보 update ) -- 확인필요
	   
	}
} 

	
 
