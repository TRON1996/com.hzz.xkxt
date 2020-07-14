package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.StuxkbMgrImpl;



public class StuxkbFactory {
    public static StuxkbMgrInterface getInstance(){
    	return new StuxkbMgrImpl();
    }
}
