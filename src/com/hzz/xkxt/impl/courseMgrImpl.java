package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Course;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.courseMgrInterface;

public class courseMgrImpl implements courseMgrInterface {

	@Override
	public int insert(Course cou) {
		// TODO Auto-generated method stub
		String sql="insert into T_Course values(?,?,?,?,?,?,?)";
		Object[] parmas={cou.getCourseID(),cou.getCourseName(),cou.getProfession().getProfessionID(),cou.getStudyTime(),cou.getCrediy(),cou.getCurriculumTime(),cou.getRemark()};
		BaseDAO dao=new BaseDAO();
		    return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Course cou) {
		// TODO Auto-generated method stub
		String sql="delete from T_Course where CourseID=?";
		Object[] parmas={cou.getCourseID()};
		BaseDAO dao=new BaseDAO();
	        return dao.delete(sql, parmas);
	}

	@Override
	public int update(Course cou) {
		// TODO Auto-generated method stub
		String sql="update T_Course set CourseName=?,ProfessionID=?,StudyTime=?,Crediy=?,CurriculumTime=?,Remark=? where CourseID=?";
		Object[] parmas={cou.getCourseName(),cou.getProfession().getProfessionID(),cou.getStudyTime(),cou.getCrediy(),cou.getCurriculumTime(),cou.getRemark(),cou.getCourseID()};
		BaseDAO dao=new BaseDAO();
			return dao.update(sql, parmas);
	}

	@Override
	public List<Course> select(String ProfessionID) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> clist=new ArrayList<Course>();
		String sql="select * from T_Course where ProfessionID=?";
		Object[] parmas={ProfessionID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Course cou=new Course();
			Profession pro=new Profession();
			pro.setProfessionID(rs.getString("ProfessionID"));
			cou.setProfession(pro);
			cou.setCourseID(rs.getString("CourseID"));
			cou.setCourseName(rs.getString("CourseName"));
			
			cou.setCurriculumTime(rs.getString("CurriculumTime"));
			cou.setStudyTime(rs.getString("StudyTime"));
			cou.setCrediy(rs.getString("Crediy"));
			cou.setRemark(rs.getString("Remark"));
			clist.add(cou);
		}
		return clist;
		
	}
	
	@Override
	public List<Course> select(String ProfessionID,String TeacherID) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> clist=new ArrayList<Course>();
		String sql="select * from T_Course where ProfessionID=?";
		Object[] parmas={ProfessionID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Course cou=new Course();
			Profession pro=new Profession();
			pro.setProfessionID(rs.getString("ProfessionID"));
			cou.setProfession(pro);
			cou.setCourseID(rs.getString("CourseID"));
			cou.setCourseName(rs.getString("CourseName"));
			
			cou.setCurriculumTime(rs.getString("CurriculumTime"));
			cou.setStudyTime(rs.getString("StudyTime"));
			cou.setCrediy(rs.getString("Crediy"));
			cou.setRemark(rs.getString("Remark"));
			if(selectTT(cou.getCourseID(), TeacherID)){
				cou.setState("ÒÑ¿ª¿Î");
			}else{
				cou.setState("Î´¿ª¿Î");
			};
			clist.add(cou);
		}
		return clist;
		
	}
	public boolean selectTT(String CourseID,String TeacherID) throws SQLException{
		
		String sql="select * from T_TeachTask where CourseID=? and TeacherID=?";
		Object[] parmas={CourseID,TeacherID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		boolean stt=false;
		while (rs.next()) {
			stt=true;
		}
		return stt;
	}

}
