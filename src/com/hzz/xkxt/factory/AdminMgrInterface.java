package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Admin;


public interface AdminMgrInterface {
   Admin login(Admin admin) throws SQLException;
   int insert(Admin admin);
   int delete(Admin admin);
   int update(Admin admin);
   List<Admin> select() throws SQLException;
   int resetpwd(Admin admin);
   int updatapwd(Admin admin); 
	
}
