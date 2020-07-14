package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Classroom;

public interface classroomMgrInterface {
	int insert(Classroom clr);
	int delete(Classroom clr);
	int update(Classroom clr);
	List<Classroom> select() throws SQLException;
} 
