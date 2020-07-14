package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.studentMgrImpl;

public class studentFactory {
   public static studentMgrInterface getInstance(){
	return new studentMgrImpl();
	   
   } 
}
