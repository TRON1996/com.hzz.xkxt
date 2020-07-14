package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Course;

public interface courseMgrInterface {
	int insert(Course cou);
	int delete(Course cou);
	int update(Course cou);
	List<Course> select(String ProfessionID) throws SQLException;
	List<Course> select(String ProfessionID, String TeacherID)
			throws SQLException;
}
