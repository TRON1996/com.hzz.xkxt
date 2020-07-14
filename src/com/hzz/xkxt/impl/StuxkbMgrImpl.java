package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Stuxkb;
import com.hzz.xkxt.factory.StuxkbMgrInterface;

public class StuxkbMgrImpl implements StuxkbMgrInterface {

	@Override
	public List<Stuxkb> select(String StudentID) throws SQLException {
		// TODO Auto-generated method stub
		List<Stuxkb> list=new ArrayList<Stuxkb>();
		String sql="select * from V_xkb where StudentID=? ";	
		Object[] parmas={StudentID};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		while(rs.next()){	
			Stuxkb sxk=new Stuxkb();
			sxk.setStudentID(rs.getString("StudentID"));
			sxk.setTeacherName(rs.getString("TeacherName"));
			sxk.setTeachTaskID(rs.getInt("ID"));
			sxk.setCourseName(rs.getString("CourseName"));
			sxk.setClassfestival(rs.getString("Classfestival"));		
			sxk.setWeekday(rs.getString("Weekday"));
			sxk.setProfessionName(rs.getString("ProfessionName"));
			sxk.setClassRoomName(rs.getString("ClassRoomName"));
			list.add(sxk);
	}
		return list;
	}
}
