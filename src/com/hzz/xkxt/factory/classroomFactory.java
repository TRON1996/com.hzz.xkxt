package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.classRoomMgrImpl;

public class classroomFactory {
   public static classroomMgrInterface getInstance(){
	return new classRoomMgrImpl();
	   
   }
}
