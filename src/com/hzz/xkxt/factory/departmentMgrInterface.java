package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Department;

public interface departmentMgrInterface {
	int insert(Department dep);
	int delete(Department dep);
	int update(Department dep);
	List<Department> select(String college) throws SQLException;
}
