package com.innoinc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.innoinc.service.SyncService;


@SpringBootApplication
public class SyncRunner implements CommandLineRunner{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SyncService syncService;

	@Override
	public void run(String... args) throws Exception {
		logger.info("sync 시작 " );
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
	   logger.info("sync 종료 " );
	   // 남은것들
	   // 1. 전화번호는 어떻게 뒤에 4자리만?? -- > 처리함.
	   // 2. 패스워드는 어떻게 백업 후 업데이트??	  --> insert 시 초기화 패스워드 , update시에는 패스워드 수정없음.
	   // 3. ir_phone에 정보 업데이트 해야함. -- 처리함
	   // 4. 퇴직자 처리 ( 그룹 생성 , 퇴직시 그룹정보 update 퇴직자에 대한 폰정보는 업데이트 하지 않음.) -- 확인
	   // 5. 로그정리 필요
	   // 6. 부서장 관련 처리 필요 ??   auth_level=7  ??
	   //    부서장들은 아래 내용이 입력 되어야 한다.
	   //	 insert into ir_user_auth_info values('team','ROOT205E','11',0,now(), '2030-01-01');
	   // 7. 컴플라이언스 처리 필요 ??  auth_level =9 ??
	  
	}
} 

	
 
