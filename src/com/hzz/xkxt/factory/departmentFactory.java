package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.departmentMgrImpl;

public class departmentFactory {
	public static departmentMgrInterface getInstance(){
		return new departmentMgrImpl();
	}
}
