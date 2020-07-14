package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Classroom;
import com.hzz.xkxt.factory.classroomMgrInterface;

public class classRoomMgrImpl implements classroomMgrInterface {

	@Override
	public int insert(Classroom clr) {
		// TODO Auto-generated method stub
		String sql="insert into T_ClassRoom values(?,?,?,?)";
		Object[] parmas={clr.getClassRoomID(),clr.getClassRoomName(),clr.getClassRoomType(),clr.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Classroom clr) {
		// TODO Auto-generated method stub
		String sql="delete from T_ClassRoom where ClassRoomID=?";
		Object[] parmas={clr.getClassRoomID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

	@Override
	public int update(Classroom clr) {
		// TODO Auto-generated method stub
		String sql="update T_ClassRoom set ClassRoomName=?,ClassRoomType=?,Remark=? where ClassRoomID=?";
		Object[] parmas={clr.getClassRoomName(),clr.getClassRoomType(),clr.getRemark(),clr.getClassRoomID()};
		BaseDAO dao=new BaseDAO();
		return dao.update(sql, parmas);
	}

	@Override
	public List<Classroom> select() throws SQLException {
		// TODO Auto-generated method stub
		List<Classroom> clist=new ArrayList<Classroom>();
		String sql="select * from T_ClassRoom";				
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql);
		while(rs.next()){
			Classroom clr=new Classroom();
			clr.setClassRoomID(rs.getString("ClassRoomID"));
			clr.setClassRoomName(rs.getString("ClassRoomName"));
			clr.setClassRoomType(rs.getString("ClassRoomType"));
			clr.setRemark(rs.getString("Remark"));
			clist.add(clr);
		}
		dao.close();
		return clist;
	}

}
