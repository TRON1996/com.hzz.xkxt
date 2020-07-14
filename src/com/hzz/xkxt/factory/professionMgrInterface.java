package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Profession;

public interface professionMgrInterface {
	int insert(Profession pro);
	int delete(Profession pro);
	int update(Profession pro);
	List<Profession> select(String department) throws SQLException;
}
