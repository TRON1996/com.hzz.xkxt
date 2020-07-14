package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Department;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.professionMgrInterface;

public class professionMgrImpl implements professionMgrInterface {

	@Override
	public int insert(Profession pro) {
		// TODO Auto-generated method stub
		String sql="insert into T_Profession values(?,?,?,?)";
		Object[] parmas={pro.getProfessionID(),pro.getProfessionName(),pro.getDepartment().getDepartmentID(),pro.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Profession pro) {
		// TODO Auto-generated method stub
		String sql="delete from T_Profession where ProfessionID=?";
		Object[] parmas={pro.getProfessionID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql, parmas);
	}

	@Override
	public int update(Profession pro) {
		// TODO Auto-generated method stub
		String sql="update T_Profession set ProfessionName=?,Remark=? where ProfessionID=?";
		Object[] parmas={pro.getProfessionName(),pro.getRemark(),pro.getProfessionID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public List<Profession> select(String department) throws SQLException {
		// TODO Auto-generated method stub
		List<Profession> plist=new ArrayList<Profession>();
		String sql="select * from T_Profession where DepartmentID=?";
	    Object[] parmas={department};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		while(rs.next()){
			Department dep=new Department();
			Profession pro=new Profession();
			dep.setDepartmentID(rs.getString("DepartmentID"));
			pro.setDepartment(dep);
		
		
		    pro.setProfessionID(rs.getString("ProfessionID"));
			pro.setProfessionName(rs.getString("ProfessionName"));
			pro.setRemark(rs.getString("Remark"));
			plist.add(pro);
		}
	
		return plist;
	}

}
