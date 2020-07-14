package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.factory.AdminMgrInterface;

public class AdminMgrImpl implements AdminMgrInterface {

	@Override
	public Admin login(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		Admin theadmin=null;
		String sql="select * from T_Admin where AdminID=? and Password=?";				
		Object[] parmas={admin.getAdminID(),admin.getPassword()};
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql,parmas);
		
		if(rs.next()){
			theadmin=new Admin();
			theadmin.setAdminID(rs.getString("AdminID"));
			theadmin.setAdminName(rs.getString("AdminName"));
			theadmin.setRole(rs.getString("Role"));
			theadmin.setPassword(rs.getString("Password"));
			theadmin.setPhoto(rs.getString("Photo"));
			theadmin.setRemark(rs.getString("Remark"));
		}				
		return theadmin;
	}	
	@Override
	public int insert(Admin admin) {
		// TODO Auto-generated method stub
		String sql="insert into T_Admin values(?,?,?,?,?,?)";
		Object[] parmas={admin.getAdminID(),admin.getAdminName(),admin.getPassword(),admin.getRole(),admin.getPhoto(),admin.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Admin admin) {
		// TODO Auto-generated method stub
		String sql="delete from T_Admin where AdminID=?";
		Object[] parmas={admin.getAdminID()};
		if(admin.getAdminID().equals("admin")){
			return 0;
		}
		BaseDAO dao=new BaseDAO();		
		return dao.delete(sql, parmas);	
	}

	@Override
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		String sql="update T_Admin set AdminName=?,Role=?,Remark=? where AdminID=?";
		Object[] parmas={admin.getAdminName(),admin.getRole(),admin.getRemark(),admin.getAdminID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}

	@Override
	public List<Admin> select() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from T_Admin";	
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql);
		List<Admin> list=new ArrayList<Admin>();
		while(rs.next()){			
			Admin theadmin=new Admin();
			theadmin.setAdminID(rs.getString("AdminID"));
			theadmin.setAdminName(rs.getString("AdminName"));
			theadmin.setRole(rs.getString("Role"));
			theadmin.setPassword(rs.getString("Password"));
			theadmin.setPhoto(rs.getString("Photo"));
			theadmin.setRemark(rs.getString("Remark"));
			list.add(theadmin);
		}				
		return list;
	}
	@Override
	public int resetpwd(Admin admin) {
		// TODO Auto-generated method stub
		String sql="update T_Admin set Password=? where AdminID=?";
		Object[] parmas={admin.getPassword(),admin.getAdminID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}
	@Override
	public int updatapwd(Admin admin) {
		// TODO Auto-generated method stub
		String sql="update T_Admin set Password=? where AdminID=?";
		Object[] parmas={admin.getPassword(),admin.getAdminID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}

}
