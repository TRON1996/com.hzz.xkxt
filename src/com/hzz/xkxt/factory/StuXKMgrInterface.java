package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.StuXK;

public interface StuXKMgrInterface {
   
   int insert(StuXK sxk);
   int delete(StuXK sxk);

   List<StuXK> select() throws SQLException;

}
