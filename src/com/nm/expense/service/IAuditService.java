package com.nm.expense.service;

import java.util.List;

import com.nm.bean.AuditRecord;

public interface IAuditService {
	List<AuditRecord> queryAuditRecord(int expenseId);
	//提交时需要添加审核记录
	boolean addAuditRecord(AuditRecord auditRecord);
}
