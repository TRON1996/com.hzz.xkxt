package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Class01;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.classMgrInterface;

public class classMgrImpl implements classMgrInterface {

	@Override
	public int insert(Class01 cla) {
		// TODO Auto-generated method stub
		String sql="insert into T_Class values(?,?,?,?)";
		Object[] parmas={cla.getClassID(),cla.getClassName(),cla.getProfession().getProfessionID(),cla.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.insert(sql, parmas);
	}

	@Override
	public int delete(Class01 cla) {
		// TODO Auto-generated method stub
		String sql="delete from T_Class where ClassID=?";
		Object[] parmas={cla.getClassID()};
		BaseDAO dao=new BaseDAO();
		return dao.delete(sql, parmas);
	}

	@Override
	public int update(Class01 cla) {
		// TODO Auto-generated method stub
		String sql="update T_Class set ClassName=?,Remark=? where ClassID=?";
		Object[] parmas={cla.getClassName(),cla.getRemark(),cla.getClassID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql,parmas);
	}

	@Override
	public List<Class01> select(String ProfessionID) throws SQLException {
		// TODO Auto-generated method stub
		List<Class01> clist=new ArrayList<Class01>();
		String sql="select * from T_Class where ProfessionID=?";
		Object[] parmas={ProfessionID};
		BaseDAO dao=new BaseDAO();
		ResultSet rs=dao.select(sql, parmas);
		while(rs.next()){
			Class01 cla=new Class01();
			Profession pro =new Profession();
			pro.setProfessionID(rs.getString("ProfessionID"));
			cla.setProfession(pro);
			cla.setClassID(rs.getString("ClassID"));
			cla.setClassName(rs.getString("ClassName"));
			
			cla.setRemark(rs.getString("Remark"));
			clist.add(cla);
		}
		return clist;
	}

}
