package com.nm.expense.service;

import java.util.List;

import com.nm.bean.AuditRecord;

public interface IAuditService {
	List<AuditRecord> queryAuditRecord(int expenseId);
	//�ύʱ��Ҫ�����˼�¼
	boolean addAuditRecord(AuditRecord auditRecord);
}
