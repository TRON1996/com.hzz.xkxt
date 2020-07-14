package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Stuxkb;




public interface StuxkbMgrInterface {
   


   List<Stuxkb> select(String StudentID) throws SQLException;

}
