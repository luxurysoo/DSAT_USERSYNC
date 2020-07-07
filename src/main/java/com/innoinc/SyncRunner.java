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
	   
	   syncService.daeshinPrint();
	   
	   syncService.addUserGroup();

   }
} 

	
 
