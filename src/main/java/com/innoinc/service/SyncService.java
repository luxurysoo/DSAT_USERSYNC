package com.innoinc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres181.ir.IrUser181;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


@Service
public class SyncService {
 
	   @Autowired
	   private IrUserService181 irUserService181;
 
	   @Autowired
	   private IrUserService irUserService;
	   
	   
	   @Value("${init_password}") 
	   private String init_password;
	   
	   private final Logger logger = LoggerFactory.getLogger(this.getClass());

	   
	   
	  
	//   List<IrUser181> irUserList181;
	   
	  
	//   IrUser181 iruser181;
	    
	//   IrPhone181 ir_phone_181;
	    
	 
	   List<IrUser181> irUserList181;
	   List<IrUser> irUserList;
	   
	    
		 
		
		@SuppressWarnings("finally")
		public int selectAll181() {
			   int cnt = -1;
			   try {
				   irUserList181 = irUserService181.selectAll();
				
				   irUserList181.forEach(irUserList -> {
						   System.out.println("userName  :  " + irUserList181.toString());
					   });
				   
			   }catch (Exception e ) {
				   logger.error(" ir_user 조회   " + e.getMessage()  );
			   }finally {
				   return cnt;
			   }
			   
		   }  
	   
	    
		@SuppressWarnings("finally")
		public int selectAll() {
			   int cnt = -1;
			   try {
				   irUserList = irUserService.selectAll();
				
				   irUserList.forEach(irUserList -> {
						   System.out.println("userName  :  " + irUserList.toString());
					   });
				   
			   }catch (Exception e ) {
				   logger.error(" ir_user 조회   " + e.getMessage()  );
			   }finally {
				   return cnt;
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
