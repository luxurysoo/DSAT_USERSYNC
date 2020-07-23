/**
 * IrUserAuthInfo table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.postgres.ir.IrUserAuthInfoMapper;
import com.innoinc.model.postgres.ir.IrUserAuthInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional		// 오류발생시 rollback 설정
public class IrUserAuthInfoService {
	@Autowired
	IrUserAuthInfoMapper irUserAuthInfoMapper;
	 
	 
	public int selectUserAuth(String id) {
		int cnt = -1;
		cnt = irUserAuthInfoMapper.selectIruserAuthByUserid(id);
		return cnt;
	}
	
  
	// bean 으로 처리
	public void updateUserAuthBean(IrUserAuthInfo ir_user_auth_info ) {
		irUserAuthInfoMapper.updateUserAuthInfoBean(ir_user_auth_info);
	}
 
	// bean 으로 처리
	public void addUserAuthBean(IrUserAuthInfo ir_user_auth_info ) {
		irUserAuthInfoMapper.insertUserAuthInfoBean(ir_user_auth_info);
	}
	
 
}
