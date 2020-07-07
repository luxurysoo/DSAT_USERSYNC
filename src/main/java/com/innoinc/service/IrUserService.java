/**
 * IrUser table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.postgres.ir.IrUserMapper;
import com.innoinc.model.postgres.ir.IrUser;

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
	
	public IrUser selectIruserByUserid(String id) {
		IrUser iruser = irUserMapper.selectIruserByUserid(id);
		return iruser;
	}
}
