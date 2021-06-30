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
		// 181에 접속 , local db에 접속
		
		// 181번 접속하여 ir_phone 가져와 백업서버  local ir_phone 에 입력한다.   phone.tbl 파일 읽기 -> 타서버에 있는데 어떻게 해야하나??
		
		// 181번 접속해서 ir_call 가져오기 today (22에 진행 예정 )   select * from ir_call where call_date =current_date and status='1';
		
		// backup 서버에 data insert ( insert into ir_call_81  .... )   // 한달만 보관할 예정
		// create table ir_call_81 as ( select * from ir_call call_date=current_date-1 );

		 
		
		 
		// 
		
		
		// 메인데이터 ir_call_81 을 조회하고 | 백업데이터 ir_call 을 조회한다. ( today - 1 기준 ) 
		// 메인데이터에 데이터가 없으면 해당 ip 를 tmp 테이블에 insert 한다.
		   
		
		// 
		//syncService.selectIruserNameByUserid181("test");
	//	syncService.selectIruserNameByUseridLocal("test");
		//syncService.getUserName181();
		//syncService.selectUserById("test");
		syncService.selectAll181();
		syncService.selectAll();
		//syncService.selectUserById181("test");
		//logger.info("sync  " + ss );
  
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
	   // 8. 채널 매핑시 퇴직자를 order by desc 로 조회하여 정보 업데이트를 진행하도록 설정함.
	   //    -> 김래수가 사번이 111 , 222  N 개이상 생길수 있음.그래서 위와같이 처리함.
	   
	}
} 

	
 
