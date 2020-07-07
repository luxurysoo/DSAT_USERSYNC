package com.innoinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innoinc.model.oracle.daishin.Daishin;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;

@Service
@Transactional
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
	   
	   public void daeshinPrint() {
		  	 daeshinList = daeshinservice.selectAll();
		  	 daeshinList.forEach(daishin -> {
		  		 System.out.println(daishin.toString());
		  	 });
		  	 
		  	 System.out.println(irUserService.selectVersion());
	   }
	   
	   public void addUserGroup() {

		  	 
		  	 //irUserGroup = new IrUserGroup();
		  	 //irUserGroup.setGroup_id("00");
		  	 //irUserGroup.setGroup_depth(1);
		  	 //irUserGroup.setGroup_name("보상");
		  	 //irUserGroup.setGroup_path("ROOT00");
		  	 //irUserGroup.setGroup_display_name("보상");
		  	 
		   	irTmpDaishinService.addGroupList(daeshinList);
		  	 
		   	 
		  	// irUserGroupList = irUserGroupService.selectAll();
		  	// irUserGroupList.forEach(i -> {
		  	//	 System.out.println(i.toString());
		  	// });
	   }
}
