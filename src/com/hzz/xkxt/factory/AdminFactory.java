package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.AdminMgrImpl;

public class AdminFactory {
 public static AdminMgrInterface getInstance(){
	return new AdminMgrImpl();
	 
 }
}
