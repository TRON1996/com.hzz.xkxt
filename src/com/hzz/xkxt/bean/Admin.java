package com.hzz.xkxt.bean;

import java.security.NoSuchAlgorithmException;

import com.hzz.xkxt.util.*;

public class Admin {
  private String AdminID;
  private String AdminName;
  private String Password;
  private String Remark;
  private String Role;
  private String Photo;
public String getAdminID() {
	return AdminID;
}
public void setAdminID(String adminID) {
	AdminID = adminID;
}
public String getAdminName() {
	return AdminName;
}
public void setAdminName(String adminName) {
	AdminName = adminName;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	try {
		Password = util.UseMD5(password);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
public String getRole() {
	return Role;
}
public void setRole(String role) {
	Role = role;
}
public String getPhoto() {
	return Photo;
}
public void setPhoto(String photo) {
	Photo = photo;
}
  
}
