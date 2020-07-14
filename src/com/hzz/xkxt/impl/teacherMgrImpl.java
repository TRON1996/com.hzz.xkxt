 package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.College;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.teacherMgrInterface;

public class teacherMgrImpl implements teacherMgrInterface {

	@Override
	public int insert(Teacher tea) {
		// TODO Auto-generated method stub
	String sql="insert into T_Teacher(TeacherID,TeacherName,CollegeID,Sex,Birthday,Culture,Password,Remark) values(?,?,?,?,?,?,?,?)";
	Object[] parmas={tea.getTeacherID(),tea.getTeacherName(),tea.getCollege().getCollegeID(),tea.getSex(),tea.getBirthday(),tea.getCulture(),tea.getPassword(),tea.getRemark()};
	BaseDAO dao=new BaseDAO();
	    return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Teacher tea) {
	String sql="delete from T_Teacher where TeacherID=?";
	Object[] parmas={tea.getTeacherID()};
	BaseDAO dao=new BaseDAO();
        return dao.delete(sql, parmas);
	}

	@Override
	public int update(Teacher tea) {
		// TODO Auto-generated method stub
	String sql="update T_Teacher set TeacherName=?,Birthday=?,Culture=?,Remark=? where TeacherID=?";
	Object[] parmas={tea.getTeacherName(),tea.getBirthday(),tea.getCulture(),tea.getRemark(),tea.getTeacherID()};
	BaseDAO dao=new BaseDAO();
		return dao.update(sql, parmas);
	}

	@Override
	public List<Teacher> select(String CollegeID) throws SQLException {
		// TODO Auto-generated method stub
		List<Teacher> tlist=new ArrayList<Teacher>();
		String sql="select * from T_Teacher where CollegeID=?";
		Object[] parmas={CollegeID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Teacher tea=new Teacher();
			College col=new College();
			col.setCollegeID(rs.getString("CollegeID"));
			tea.setCollege(col);
			tea.setTeacherID(rs.getString("TeacherID"));
			tea.setTeacherName(rs.getString("TeacherName"));
			tea.setSex(rs.getString("Sex"));
			tea.setBirthday(rs.getString("Birthday"));
			tea.setCulture(rs.getString("Culture"));
			tea.setPassword(rs.getString("Password"));
			tea.setPhoto(rs.getString("Photo"));
			tea.setRemark(rs.getString("Remark"));
			tlist.add(tea);
		}
		return tlist;
	}

	@Override
	public Teacher login(Teacher tea) throws SQLException {
		// TODO Auto-generated method stub
		Teacher thetea=null;
		String sql="select * from T_Teacher where TeacherID=? and Password=?";
		Object[]parmas={tea.getTeacherID(),tea.getPassword()};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		
		if(rs.next()){
			thetea=new Teacher();
			College co=new College();
	     	co.setCollegeID(rs.getString("CollegeID"));
			thetea.setCollege(co);
			thetea.setTeacherID(rs.getString("TeacherID"));
			thetea.setTeacherName(rs.getString("TeacherName"));
			thetea.setSex(rs.getString("Sex"));
			thetea.setBirthday(rs.getString("Birthday"));
			thetea.setCulture(rs.getString("Culture"));
			thetea.setPassword(rs.getString("Password"));
			thetea.setPhoto(rs.getString("Photo"));
			thetea.setRemark(rs.getString("Remark"));
		}				
		return thetea;
	}

	@Override
	public int updata1(Teacher tea) {
		// TODO Auto-generated method stub
		String sql="update T_Teacher set TeacherName=?,CollegeID=?,Sex=?,Birthday=?,Culture=?,Password=?,Remark=? where TeacherID=?";
		Object[] parmas={tea.getTeacherName(),tea.getCollege().getCollegeID(),tea.getSex(),tea.getBirthday(),tea.getCulture(),tea.getPassword(),tea.getRemark(),tea.getTeacherID()};
		BaseDAO dao=new BaseDAO();
			return dao.update(sql, parmas);
	}
	@Override
	public int resetpwd(Teacher tea) {
		// TODO Auto-generated method stub
		String sql="update T_Teacher set Password=? where TeacherID=?";
		Object[] parmas={tea.getPassword(),tea.getTeacherID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}
}
