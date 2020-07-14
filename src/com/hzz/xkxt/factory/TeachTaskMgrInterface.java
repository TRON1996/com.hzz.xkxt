package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.TeachTask;



public interface TeachTaskMgrInterface {
	boolean insert(TeachTask tt);
	boolean delete(TeachTask tt);
	int updata(TeachTask tt);
	List<TeachTask> select(String TeacherID) throws SQLException;
	List<TeachTask> select(String Curricula, String TeacherID) throws SQLException;

}
