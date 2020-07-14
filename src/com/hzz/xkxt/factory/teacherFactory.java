package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.teacherMgrImpl;

public class teacherFactory {
    public static teacherMgrInterface getInstance(){
    	return new teacherMgrImpl();
    }
}
