package com.nm.system.service.impl;

import java.util.List;

import com.nm.bean.Cost;
import com.nm.system.dao.ICostDao;
import com.nm.system.dao.impl.CostDaoImpl;
import com.nm.system.service.ICostService;

public class CostServiceImpl implements ICostService{
ICostDao costDao=new CostDaoImpl();
	@Override
	public boolean addCost(Cost cost) {
		// TODO Auto-generated method stub
		int rows=costDao.addCost(cost);
		if(rows>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delCost(Cost cost) {
		// TODO Auto-generated method stub
		int rows=costDao.delCost(cost);
		if(rows>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCost(Cost cost) {
		int rows=costDao.updateCost(cost);
		if(rows>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Cost> queryCost(Cost cost) {
		// TODO Auto-generated method stub
		return costDao.queryCost(cost);
	}

}
