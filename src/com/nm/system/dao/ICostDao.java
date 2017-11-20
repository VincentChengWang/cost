package com.nm.system.dao;

import java.util.List;

import com.nm.bean.Cost;

public interface ICostDao {
int addCost(Cost cost);
int delCost(Cost cost);
int updateCost(Cost cost);
List<Cost> queryCost(Cost cost);
}
