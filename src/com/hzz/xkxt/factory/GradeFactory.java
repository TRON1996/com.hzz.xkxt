package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.GradeMgrImpl;



public class GradeFactory {
 public static GradeMgrInterface getInstance(){
	return new GradeMgrImpl();
	 
 }
}
