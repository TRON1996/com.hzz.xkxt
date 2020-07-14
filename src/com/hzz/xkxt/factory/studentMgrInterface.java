package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Student;

public interface studentMgrInterface {
	int insert(Student stu);
	int delete(Student stu);
	int update(Student stu);
	List<Student> select(String ClassID) throws SQLException;
	Student login(Student stu) throws SQLException;
	int upp(Student stu);
	int resetpwd(Student stu);
}
