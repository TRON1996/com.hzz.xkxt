package com.hzz.xkxt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzz.xkxt.basicdao.BaseDAO;
import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.bean.Notice;
import com.hzz.xkxt.factory.NoticeInterface;


public class NoticeInImpl implements NoticeInterface {

	@Override
	public boolean insert(Notice notice) {
		// TODO Auto-generated method stub
		String SQL="insert into T_Notice (AdminID,NoticeTitle,NoticeContent,ReleaseTime) values (?,?,?,?)";
		Object [] parmas={notice.getAdmin().getAdminID(),notice.getNoticeTitle(),notice.getNoticeContent(),notice.getReleaseTime()};
		int count=new BaseDAO().insert(SQL, parmas);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Notice> select() {
		// TODO Auto-generated method stub
		List<Notice> list=new ArrayList<Notice>();
		String sql="select top 10 *  from T_Notice order by ReleaseTime desc";
		ResultSet rs=new BaseDAO().select(sql);
		try {
			while(rs.next()){
				Notice no=new Notice();
				no.setID(rs.getInt("ID"));
				Admin admin=new Admin();
				admin.setAdminID(rs.getString("AdminID"));
				no.setAdmin(admin);
				no.setNoticeTitle(rs.getString("NoticeTitle"));
				no.setNoticeContent(rs.getString("NoticeContent"));
				no.setReleaseTime(rs.getString("ReleaseTime"));
				list.add(no);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}

	@Override
	public boolean delete(Notice notice) {
		// TODO Auto-generated method stub
		String SQL="delete from T_Notice where ID=?";
		Object[] parmas={notice.getID()};
		int count=new BaseDAO().delete(SQL,parmas);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public Notice Noselect(String ID) {
		// TODO Auto-generated method stub
		Notice no=null;
		String sql="select *  from T_Notice where ID=?";
		Object[] parmas={ID};
		ResultSet rs=new BaseDAO().select(sql,parmas);
		try {
			while(rs.next()){
				no=new Notice();
				no.setID(rs.getInt("ID"));
				Admin admin=new Admin();
				admin.setAdminID(rs.getString("AdminID"));
				no.setAdmin(admin);
				no.setNoticeTitle(rs.getString("NoticeTitle"));
				no.setNoticeContent(rs.getString("NoticeContent"));
				no.setReleaseTime(rs.getString("ReleaseTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no;
	}

	

}
