package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.TeachTask;
import com.hzz.xkxt.bean.Course;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.TeachTaskMgrInterface;

public class TeachTaskMgrImpl implements TeachTaskMgrInterface {

	@Override
	public boolean insert(TeachTask tt) {
		// TODO Auto-generated method stub
		String sql="insert into T_TeachTask(TeacherID,CourseID,Curricula,Auditing) values(?,?,?,?)";
		Object[] parmas={tt.getTeacherID(),tt.getCourse().getCourseID(),tt.getCurricula(),tt.getAuditing()};		
		BaseDAO dao=new BaseDAO();		
		int count=dao.insert(sql, parmas);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(TeachTask tt) {
		// TODO Auto-generated method stub
		String sql="delete from T_TeachTask where TeacherID=? and CourseID=?";
		Object[] parmas={tt.getTeacherID(),tt.getCourse().getCourseID()};
		BaseDAO dao=new BaseDAO();	
		int count=dao.delete(sql, parmas);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public List<TeachTask> select(String TeacherID) throws SQLException {
		// TODO Auto-generated method stub
	List<TeachTask> tlist=new ArrayList<TeachTask>();
		String sql="select * from V_TeachTask where TeacherID=?";				
		BaseDAO dao=new BaseDAO();	
		Object[] parmas={TeacherID};
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Course Course=new Course();
			Course.setCourseID(rs.getString("CourseID"));
			TeachTask tt=new TeachTask();
			tt.setCourse(Course);
			tt.setID(rs.getString("ID"));
			tt.setTeacherID(rs.getString("TeacherID"));
			tt.setCurricula(rs.getString("Curricula"));
			tt.setAuditing(rs.getString("Auditing"));
			String sql2="select * from T_Course where CourseID=?";
			String CourseID =rs.getString("CourseID");
			Object[] parmas2={CourseID};
			BaseDAO dao2= new BaseDAO();
			ResultSet rs2= dao2.select(sql2, parmas2);
			while(rs2.next()){
				Profession pro = new Profession();
			    Course.setCourseID(rs2.getString("CourseID"));
				Course.setCourseName(rs2.getString("CourseName"));
				Course.setCrediy(rs2.getString("Crediy"));
				Course.setStudyTime(rs2.getString("StudyTime"));
				String sql3="select * from T_Profession where ProfessionID=?";
				Object[] parmas3={rs2.getString("ProfessionID")};
				BaseDAO dao3= new BaseDAO();
				ResultSet rs3= dao3.select(sql3, parmas3);
				while(rs3.next()){
					pro.setProfessionName(rs3.getString("ProfessionName"));
					Course.setProfession(pro);
				}
			}
			tt.setCourse(Course);
			tlist.add(tt);
		}
		return tlist;
	}

	@Override
	public int updata(TeachTask tt) {
		// TODO Auto-generated method stub
		String sql="update T_TeachTask set Auditing=? where ID=?";
		Object[] parmas={tt.getAuditing(),tt.getID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}

	@Override
	public List<TeachTask> select(String Curricula, String TeacherID) throws SQLException {
		// TODO Auto-generated method stub
		String SQL="select * from V_TeachTask where Curricula like ? and TeacherID=?";
		Object[] parmas={Curricula,TeacherID};
		ResultSet rs=new BaseDAO().select(SQL,parmas);
		List<TeachTask> list=new ArrayList<TeachTask>();
		while(rs.next()){
			Course Course=new Course();
			Course.setCourseID(rs.getString("CourseID"));
			TeachTask tt=new TeachTask();
			tt.setCourse(Course);
			tt.setID(rs.getString("ID"));
			tt.setTeacherID(rs.getString("TeacherID"));
			tt.setCurricula(rs.getString("Curricula"));
			tt.setAuditing(rs.getString("Auditing"));
			String sql2="select * from T_Course where CourseID=?";
			String CourseID =rs.getString("CourseID");
			Object[] parmas2={CourseID};
			BaseDAO dao2= new BaseDAO();
			ResultSet rs2= dao2.select(sql2, parmas2);
			while(rs2.next()){
				Profession pro = new Profession();
			    Course.setCourseID(rs2.getString("CourseID"));
				Course.setCourseName(rs2.getString("CourseName"));
				Course.setCrediy(rs2.getString("Crediy"));
				Course.setStudyTime(rs2.getString("StudyTime"));
				String sql3="select * from T_Profession where ProfessionID=?";
				Object[] parmas3={rs2.getString("ProfessionID")};
				BaseDAO dao3= new BaseDAO();
				ResultSet rs3= dao3.select(sql3, parmas3);
				while(rs3.next()){
					pro.setProfessionName(rs3.getString("ProfessionName"));
					Course.setProfession(pro);
				}
			}
			tt.setCourse(Course);
			list.add(tt);
		}
		return list;
	}

}
