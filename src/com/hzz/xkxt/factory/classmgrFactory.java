package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.classMgrImpl;


public class classmgrFactory {
	public static classMgrInterface getInstance(){
		return new classMgrImpl();
		   
	   }
}
