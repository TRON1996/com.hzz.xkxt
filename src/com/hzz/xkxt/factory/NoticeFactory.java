package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.NoticeInImpl;



public class NoticeFactory {

	

	public static NoticeInterface NoticeInterFace(){
		return new NoticeInImpl();
	}

}
