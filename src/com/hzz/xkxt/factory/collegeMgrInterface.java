package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.College;

public interface collegeMgrInterface {
	int insert(College col);
	int delete(College col);
	int update(College col);
	List<College> select() throws SQLException;
}
