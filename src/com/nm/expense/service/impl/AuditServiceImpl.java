package com.nm.expense.service.impl;

import java.util.List;

import com.nm.bean.AuditRecord;
import com.nm.expense.dao.IAuditDao;
import com.nm.expense.dao.impl.AuditDaoImpl;
import com.nm.expense.service.IAuditService;

public class AuditServiceImpl implements IAuditService{
	IAuditDao auditDao=new AuditDaoImpl();
	@Override
	public List<AuditRecord> queryAuditRecord(int expenseId) {
		// TODO Auto-generated method stub
		
		return auditDao.queryAuditRecord(expenseId);
	}

	@Override
	public boolean addAuditRecord(AuditRecord auditRecord) {
		// TODO Auto-generated method stub
		int rows=auditDao.addAuditRecord(auditRecord);
		if(rows>0){
			return true;
		}
		return false;
	}

}
