package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.professionMgrImpl;

public class professionFactory {
   public static professionMgrInterface getInstance(){
	return new professionMgrImpl();
	   
   }
}
