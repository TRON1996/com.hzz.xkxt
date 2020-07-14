package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.StuXKMgrImpl;



public class StuXKFactory {
    public static StuXKMgrInterface getInstance(){
    	return new StuXKMgrImpl();
    }
}
