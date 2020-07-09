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
	}
} 

	
 
