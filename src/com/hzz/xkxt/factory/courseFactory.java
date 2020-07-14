package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.courseMgrImpl;

public class courseFactory {
   public static courseMgrInterface getInstance(){
	return new courseMgrImpl();
            
   } 
}