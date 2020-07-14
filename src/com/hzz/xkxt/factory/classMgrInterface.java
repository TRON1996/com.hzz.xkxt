package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Class01;

public interface classMgrInterface {
	int insert(Class01 cla);
	int delete(Class01 cla);
	int update(Class01 cla);
	List<Class01> select(String ProfessionID) throws SQLException;
}
