package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;



import com.hzz.xkxt.bean.Teacher;

public interface teacherMgrInterface {
	int insert(Teacher tea);
	int delete(Teacher tea);
	int update(Teacher tea);
	List<Teacher> select(String CollegeID) throws SQLException;
	Teacher login(Teacher tea) throws SQLException;
	int updata1(Teacher tea);
	int resetpwd(Teacher tea);
}
