package com.nm.expense.dao;

import java.util.List;

import com.nm.bean.AuditRecord;

public interface IAuditDao {
	List<AuditRecord> queryAuditRecord(int expenseId);
	int addAuditRecord(AuditRecord auditrecord);
}
