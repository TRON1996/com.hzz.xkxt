package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.TeachTaskMgrImpl;


public class TeachTaskFactory {
	public static TeachTaskMgrInterface getInstance(){
		return new TeachTaskMgrImpl();
		   
	   }
}
