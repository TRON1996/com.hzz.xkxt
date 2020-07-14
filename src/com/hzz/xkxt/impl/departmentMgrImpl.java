package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.College;
import com.hzz.xkxt.bean.Department;
import com.hzz.xkxt.factory.departmentMgrInterface;

public class departmentMgrImpl implements departmentMgrInterface {

	@Override
	public int insert(Department dep) {
		// TODO Auto-generated method stub
		String sql="insert into T_Department values(?,?,?,?)";
		Object[] parmas={dep.getDepartmentID(),dep.getDepartmentName(),dep.getCollege().getCollegeID(),dep.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Department dep) {
		// TODO Auto-generated method stub
		String sql="delete from T_Department where DepartmentID=?";
		Object[] parmas={dep.getDepartmentID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql,parmas);
	}

	@Override
	public int update(Department dep) {
		// TODO Auto-generated method stub
		String sql="update T_Department set DepartmentName=?,Remark=? where DepartmentID=?";
		Object[] parmas={dep.getDepartmentName(),dep.getRemark(),dep.getDepartmentID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public List<Department> select(String college) throws SQLException {
		// TODO Auto-generated method stub
		List<Department> dlist=new ArrayList<Department>();
		String sql="select * from T_Department where CollegeID=?";
	    Object[] parmas={college};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		while(rs.next()){
			Department dep=new Department();
			College col =new College();
			col.setCollegeID(rs.getString("CollegeID"));
			dep.setCollege(col);
			
			dep.setDepartmentID(rs.getString("DepartmentID"));
			dep.setDepartmentName(rs.getString("DepartmentName"));
			dep.setRemark(rs.getString("Remark"));
			dlist.add(dep);
		}
		dao.close();
		return dlist;
	}


	
}
