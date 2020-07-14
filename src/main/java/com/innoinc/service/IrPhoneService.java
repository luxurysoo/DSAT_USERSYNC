/**
 * IrPhone table service
 * 
 * DAO에 구현된 메소드를 사용
 */

package com.innoinc.service;

import java.util.List;

import com.innoinc.dao.postgres.ir.IrPhoneMapper;
import com.innoinc.dao.postgres.ir.IrUserMapper;
import com.innoinc.model.postgres.ir.IrPhone;
import com.innoinc.model.postgres.ir.IrUser;
import com.innoinc.model.postgres.ir.IrUserGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional		// 오류발생시 rollback 설정
public class IrPhoneService {
	@Autowired
    IrPhoneMapper irPhoneMapper;
	
	public String selectVersion() {
		return irPhoneMapper.getVersion();
	}
	
	 
  
	// bean 으로 처리
	public void updatePhoneBean(IrPhone irPhone ) {
		irPhoneMapper.updatePhoneBean(irPhone);
	}
	
 
}
