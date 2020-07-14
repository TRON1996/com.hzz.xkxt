package com.hzz.xkxt.factory;

import com.hzz.xkxt.factory.collegeMgrInterface;
import com.hzz.xkxt.impl.collegeMgrImpl;

public class collegeFactory {
	public static collegeMgrInterface getInstance(){
		return new collegeMgrImpl();
	}
}
