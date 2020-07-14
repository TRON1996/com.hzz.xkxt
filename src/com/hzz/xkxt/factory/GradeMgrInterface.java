package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Grade;


public interface GradeMgrInterface {

   int update(Grade gr);
   List<Grade> select() throws SQLException;
   
	
}
