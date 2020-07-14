package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.bean.Audit;
import com.hzz.xkxt.factory.AuditMgrInterface;

public class AuditMgrImpl implements AuditMgrInterface {

	@Override
	public List<Audit> select() throws SQLException {
		// TODO Auto-generated method stub
		List<Audit> list=new ArrayList<Audit>();
		String sql="select * from V_Audit ";	
		BaseDAO dao=new BaseDAO();	
		ResultSet rs=dao.select(sql);	
		while(rs.next()){						
			Audit aud=new Audit();	
			Admin admin=new Admin();
			aud.setTeacherID(rs.getString("TeacherID"));
			aud.setTeacherName(rs.getString("TeacherName"));
			aud.setAuditing(rs.getString("Auditing"));
			aud.setCourseID(rs.getString("CourseID"));
			aud.setCourseName(rs.getString("CourseName"));
			aud.setTeachTaskID(rs.getString("ID"));
		/*    aud.setAuditDate(rs.getString("AuditDate"));
		    admin.setAdminID(rs.getString("AdminID"));*/
		    aud.setAdmin(admin);
			list.add(aud);
		}				
		return list;
	}

	@Override
	public int insert(Audit audit) {
		// TODO Auto-generated method stub
		String sql="insert into T_Audit(TeachTaskID,AuditDate,AdminID,Remark) values(?,?,?,?)";
		Object[] parmas={audit.getTeachTaskID(),audit.getAuditDate(),audit.getAdmin().getAdminID(),audit.getRemark()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}

	@Override
	public int updata(Audit audit) {
		// TODO Auto-generated method stub
		String sql="update T_TeachTask set Auditing where ID=?";
		Object[] parmas={audit.getAuditing(),audit.getID()};		
		BaseDAO dao=new BaseDAO();		
		return dao.update(sql, parmas);
	}

	@Override
	public int delete(Audit audit) {
		// TODO Auto-generated method stub
		String sql="delete from T_Audit where TeachTaskID=?";
		Object[] parmas={audit.getTeachTaskID()};
		BaseDAO dao=new BaseDAO();		
		return dao.delete(sql, parmas);	
	}

}
