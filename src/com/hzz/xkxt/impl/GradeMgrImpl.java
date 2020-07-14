package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Grade;
import com.hzz.xkxt.factory.GradeMgrInterface;

public class GradeMgrImpl implements GradeMgrInterface {


	@Override
	public int update(Grade gr) {
		// TODO Auto-generated method stub
		String sql="update T_CourseSchedule set ScoreOne=?,ScoreTwo=?,ScoreThree=?,TotalScore=? where StudentID=? and TeachTaskID=?";
		Object[] parmas={gr.getScoreOne(),gr.getScoreTwo(),gr.getScoreThree(),gr.getTotalScore(),gr.getStudentID(),gr.getTeachTaskID()};
		BaseDAO dao=new BaseDAO();
			return dao.update(sql, parmas);
	}

	@Override
	public List<Grade> select() throws SQLException {
		// TODO Auto-generated method stub
		List<Grade> clist=new ArrayList<Grade>();
		String sql="select * from V_Grade";
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql);
		while(rs.next()){
			Grade gr=new Grade();
			gr.setCourseName(rs.getString("CourseName"));
			gr.setStudentName(rs.getString("StudentName"));
			gr.setCurricula(rs.getString("Curricula"));
			gr.setCrediy(rs.getString("Crediy"));
			gr.setID(rs.getString("ID"));
			gr.setScoreOne(rs.getString("ScoreOne"));
			gr.setScoreTwo(rs.getString("ScoreTwo"));
			gr.setScoreThree(rs.getString("ScoreThree"));
			gr.setTotalScore(rs.getString("TotalScore"));
			gr.setStudentID(rs.getString("StudentID"));
			clist.add(gr);
		}
		return clist;
	}

}
