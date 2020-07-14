package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.College;
import com.hzz.xkxt.factory.collegeMgrInterface;

public class collegeMgrImpl implements collegeMgrInterface {

	@Override
	public int insert(College col) {
		// TODO Auto-generated method stub
		String sql="insert into T_College values(?,?,?)";
		Object[] parmas={col.getCollegeID(),col.getCollegeName(),col.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(College col) {
		// TODO Auto-generated method stub
		String sql="delete from T_College where CollegeID=?";
		Object[] parmas={col.getCollegeID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

	@Override
	public int update(College col) {
		// TODO Auto-generated method stub
		String sql="update T_College set CollegeName=?,Remark=? where CollegeID=?";
		Object[] parmas={col.getCollegeName(),col.getRemark(),col.getCollegeID()};
		BaseDAO dao=new BaseDAO();
		return dao.update(sql, parmas);
	}

	@Override
	public List<College> select() throws SQLException {
		// TODO Auto-generated method stub
		List<College> clist=new ArrayList<College>();
		String sql="select * from T_College";				
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql);
		while(rs.next()){
			College col=new College();
			col.setCollegeID(rs.getString("collegeID"));
			col.setCollegeName(rs.getString("collegeName"));
			col.setRemark(rs.getString("Remark"));
			clist.add(col);
		}
		dao.close();
		return clist;
	}

}
