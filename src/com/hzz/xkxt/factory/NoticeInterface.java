package com.hzz.xkxt.factory;

import java.util.List;

import com.hzz.xkxt.bean.Notice;


;

public interface NoticeInterface {
	boolean insert(Notice notice);
	List<Notice> select();
	boolean delete(Notice notice);
	Notice Noselect(String ID);

}
