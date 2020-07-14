/**
 * IrUser table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.postgres.ir.IrUserMapper;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional		// 오류발생시 rollback 설정
public class IrUserService {
	@Autowired
    IrUserMapper irUserMapper;
	
	public String selectVersion() {
		return irUserMapper.getVersion();
	}
	
	public List<IrUser> selectAll() {
		List<IrUser> iruserList = irUserMapper.selectAll();
		return iruserList;
	}
	
	public int selectUser(String id) {
		int cnt = -1;
		cnt = irUserMapper.selectIruserByUserid(id);
		return cnt;
	}
	
	public void addUserList(List<IrUser> userList) {
		irUserMapper.insertUserList(userList);
	}
	// bean 으로 처리
	public void addUserBean(IrUser irUser ) {
		irUserMapper.insertUserBean(irUser);
	}
	
	// bean 으로 처리
	public void updateUserBean(IrUser irUser ) {
		irUserMapper.updateUserBean(irUser);
	}
	
	
	public void removeIrUser() {
		irUserMapper.deleteIrUser();
		
	} 

}
