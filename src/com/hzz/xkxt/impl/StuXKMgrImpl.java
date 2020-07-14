package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.StuXK;
import com.hzz.xkxt.factory.StuXKMgrInterface;

public class StuXKMgrImpl implements StuXKMgrInterface {

	@Override
	public int insert(StuXK sxk) {
		// TODO Auto-generated method stub
		String sql="insert into T_CourseSchedule(StudentID,TeachTaskID,ScoreOne,ScoreTwo,ScoreThree,TotalScore) values(?,?,?,?,?,?)";
		Object[] parmas={sxk.getStudentID(),sxk.getTeachTaskID(),sxk.getScoreOne(),sxk.getScoreTwo(),sxk.getScoreThree(),sxk.getTotalScore()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(StuXK sxk) {
		// TODO Auto-generated method stub
		String sql="delete from T_CourseSchedule where TeachTaskID=?";
		Object[] parmas={sxk.getTeachTaskID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

	@Override
	public List<StuXK> select() throws SQLException {
		// TODO Auto-generated method stub
		List<StuXK> list=new ArrayList<StuXK>();
		String sql="select * from V_Stuxk ";	
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql);
		while(rs.next()){	
			StuXK sxk=new StuXK();
			sxk.setCourseName(rs.getString("CourseName"));
			sxk.setTeacherName(rs.getString("TeacherName"));
			sxk.setWeekday(rs.getString("Weekday"));
			sxk.setClassfestival(rs.getString("Classfestival"));
			sxk.setClassRoomName(rs.getString("ClassRoomName"));
			sxk.setCrediy(rs.getString("Crediy"));
			sxk.setTeachTaskID(rs.getInt("ID"));
			sxk.setProfessionName(rs.getString("ProfessionName"));
			list.add(sxk);
		}
		return list;
	}

}
