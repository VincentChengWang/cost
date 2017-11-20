package com.nm.system.service;

import java.util.List;

import com.nm.bean.Cost;

public interface ICostService {
	boolean addCost(Cost cost);
	boolean delCost(Cost cost);
	boolean updateCost(Cost cost);
	List<Cost> queryCost(Cost cost);
}
