package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Schedule;
import com.hzz.xkxt.bean.TeachTask;
import com.hzz.xkxt.bean.Classroom;
import com.hzz.xkxt.bean.Course;
import com.hzz.xkxt.factory.ScheduleMgrInterface;

public class ScheduleMgrImpl implements ScheduleMgrInterface {

	@Override
	public List<Schedule> select(String TeacherID) throws SQLException {
		// TODO Auto-generated method stub
		List<Schedule> list= new ArrayList<Schedule>();
		String sql="select * from V_Schedule1 where TeacherID=?";
		Object[] parmas={TeacherID};
		BaseDAO dao= new BaseDAO();
		ResultSet rs= dao.select(sql, parmas);
		while (rs.next()) {
			Schedule sch =new Schedule();
			sch.setID(rs.getString("ID"));
			sch.setClassfestival(rs.getString("Classfestival"));
			sch.setClassTime(rs.getString("ClassTime"));
			sch.setWeekday(rs.getString("Weekday"));
			sch.setCourseName(rs.getString("CourseName"));
			sch.setRemark(rs.getString("Remark"));
			Classroom classRoom =new Classroom();
			classRoom.setClassRoomName(rs.getString("ClassRoomName"));
			classRoom.setClassRoomType(rs.getString("ClassRoomType"));
			sch.setClassRoom(classRoom);
			
			TeachTask teachTask =new TeachTask();
			
			String TeachTaskID =rs.getString("ID");
			teachTask.setTeacherID(rs.getString("TeacherID"));
			String sql2="select * from V_TeachTask where ID=?";
			Object[] parmas2={TeachTaskID};
			BaseDAO dao2= new BaseDAO();
			ResultSet rs2= dao2.select(sql2, parmas2);
			while(rs2.next()){
				Course Course =new Course();

				Course.setCourseName(rs2.getString("CourseName"));
				teachTask.setCourse(Course);
				sch.setTeachTask(teachTask);
				
				

			}
			list.add(sch);
		}
		return list;
	}

	

	@Override
	public int insert(Schedule sch) {
		// TODO Auto-generated method stub
		String sql="insert into T_Schedule values(?,?,?,?,?,?)";
		Object[] parmas={sch.getTeachTaskID(),sch.getClassRoomID(),sch.getClassTime(),sch.getClassfestival(),sch.getWeekday(),sch.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Schedule sch) {
		// TODO Auto-generated method stub
		String sql="delete from T_Schedule where TeachTaskID=?";
		Object[] parmas={sch.getTeachTaskID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

}
