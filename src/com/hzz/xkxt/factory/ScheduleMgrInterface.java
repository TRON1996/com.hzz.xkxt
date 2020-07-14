package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;




import com.hzz.xkxt.bean.Schedule;

public interface ScheduleMgrInterface {
	 List<Schedule> select(String TeacherID) throws SQLException;
	   int insert(Schedule sch);
	   int delete(Schedule sch);
}
