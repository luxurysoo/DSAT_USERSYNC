/**
 * IrUser table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.postgres181.ir.IrUserMapper181;
import com.innoinc.model.postgres181.ir.IrUser181;
import com.innoinc.model.postgres181.ir.IrUserGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional		// 오류발생시 rollback 설정
public class IrUserService181 {
	@Autowired
    IrUserMapper181 irUserMapper181;
	
	public String selectVersion() {
		return irUserMapper181.getVersion();
	}
	
	public List<IrUser181> selectAll() {
		List<IrUser181> iruserList = irUserMapper181.selectAll();
		return iruserList;
	}
	
	public int selectUser(String id) {
		int cnt = -1;
		cnt = irUserMapper181.selectIruserByUserid(id);
		return cnt;
	}
	
	public void addUserList(List<IrUser181> userList) {
		irUserMapper181.insertUserList(userList);
	}
	// bean 으로 처리
	public void addUserBean(IrUser181 irUser181 ) {
		irUserMapper181.insertUserBean(irUser181);
	}
	
	// bean 으로 처리
	public void updateUserBean(IrUser181 irUser181 ) {
		irUserMapper181.updateUserBean(irUser181);
	}
	
	
	public void removeIrUser() {
		irUserMapper181.deleteIrUser();
		
	} 

}
