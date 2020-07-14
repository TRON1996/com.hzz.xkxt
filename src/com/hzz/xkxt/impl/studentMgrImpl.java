package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Class01;
import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.studentMgrInterface;

public class studentMgrImpl implements studentMgrInterface {

	@Override
	public int insert(Student stu) {
		// TODO Auto-generated method stub
		String sql="insert into T_Student(StudentID,StudentName,Sex,Birth,ClassID,Password,Remark) values(?,?,?,?,?,?,?)";
		Object[] parmas={stu.getStudentID(),stu.getStudentName(),stu.getSex(),stu.getBirth(),stu.getClassmgr().getClassID(),stu.getPassword(),stu.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Student stu) {
		// TODO Auto-generated method stub
		String sql="delete from T_Student where StudentID=?";
	    Object[] parmas={stu.getStudentID()};
	    BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

	@Override
	public int update(Student stu) {
		// TODO Auto-generated method stub
		String sql="update T_Student set StudentName=?,Sex=?,Birth=?,ClassID=?,Photo=?,Remark=? where StudentID=?";
		Object[] parmas={stu.getStudentName(),stu.getSex(),stu.getBirth(),stu.getClassmgr().getClassID(),stu.getPhoto(),stu.getRemark(),stu.getStudentID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql, parmas);
	}

	@Override
	public List<Student> select(String ClassID) throws SQLException {
		// TODO Auto-generated method stub
		List<Student> slist=new ArrayList<Student>();
		String sql="select * from T_Student where ClassID=?";
		Object[] parmas={ClassID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Student stu=new Student();
			Class01 cla=new Class01();
			cla.setClassID(rs.getString("ClassID"));
			stu.setClassmgr(cla);
			stu.setStudentID(rs.getString("StudentID"));
			stu.setStudentName(rs.getString("StudentName"));
			stu.setSex(rs.getString("Sex"));
			stu.setBirth(rs.getString("Birth"));
	        stu.setPhoto(rs.getString("Photo"));
			stu.setRemark(rs.getString("Remark"));
			stu.setPassword(rs.getString("Password"));
			slist.add(stu);
		}
		return slist;
	}

	@Override
	public Student login(Student stu) throws SQLException {
		// TODO Auto-generated method stub
		Student thestu=null;
		String sql="select * from T_Student where StudentID=? and Password=?";
		Object[]parmas={stu.getStudentID(),stu.getPassword()};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		
		if(rs.next()){
			thestu=new Student();
			Class01 cla=new Class01();
			cla.setClassID(rs.getString("ClassID"));
			thestu.setClassmgr(cla);
			thestu.setStudentID(rs.getString("studentID"));
			thestu.setStudentName(rs.getString("studentName"));
			thestu.setSex(rs.getString("Sex"));
			thestu.setBirth(rs.getString("Birth"));
			thestu.setPassword(rs.getString("Password"));
			thestu.setPhoto(rs.getString("Photo"));
			thestu.setRemark(rs.getString("Remark"));
		}				
		return thestu;
	}



	@Override
	public int upp(Student stu) {
		// TODO Auto-generated method stub
		String sql="update T_Student set StudentName=?,Sex=?,Birth=?,Remark=? where StudentID=?";
		Object[] parmas={stu.getStudentName(),stu.getSex(),stu.getBirth(),stu.getRemark(),stu.getStudentID()};
		BaseDAO dao=new BaseDAO();
	    return dao.update(sql, parmas);
	}

	@Override
	public int resetpwd(Student stu) {
		// TODO Auto-generated method stub
		String sql="update T_Student set Password=? where StudentID=?";
		Object[] parmas={stu.getPassword(),stu.getStudentID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}
}
