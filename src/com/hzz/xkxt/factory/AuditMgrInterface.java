package com.hzz.xkxt.factory;

import java.sql.SQLException;
import java.util.List;

import com.hzz.xkxt.bean.Audit;

public interface AuditMgrInterface {
	 List<Audit> select() throws SQLException;
	
	int insert(Audit audit);
	int updata(Audit audit);
	int delete(Audit audit);
}
