package com.hzz.xkxt.factory;

import com.hzz.xkxt.impl.AuditMgrImpl;

public class AuditFactory {
	public static AuditMgrInterface getInstance(){
		return new AuditMgrImpl();
	
	}
}
