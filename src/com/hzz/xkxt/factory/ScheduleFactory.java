package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.ScheduleMgrImpl;

public class ScheduleFactory {
   public static ScheduleMgrInterface getInstance(){
	return new ScheduleMgrImpl();
	   
   } 
}
