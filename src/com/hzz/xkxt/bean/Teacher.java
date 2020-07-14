package com.hzz.xkxt.bean;

public class Teacher {
 private String TeacherID;
 private String TeacherName;
 private College College;
 private String Sex;
 private String Birthday;
 private String Culture;
 private String Password;
 private String Photo;
 public String getPhoto() {
	return Photo;
}
public void setPhoto(String photo) {
	Photo = photo;
}
private String Remark;
public String getTeacherID() {
	return TeacherID;
}
public void setTeacherID(String teacherID) {
	TeacherID = teacherID;
}
public String getTeacherName() {
	return TeacherName;
}
public void setTeacherName(String teacherName) {
	TeacherName = teacherName;
}

public College getCollege() {
	return College;
}
public void setCollege(College College) {
	this.College = College;
}
public String getSex() {
	return Sex;
}
public void setSex(String sex) {
	Sex = sex;
}
public String getBirthday() {
	return Birthday;
}
public void setBirthday(String birthday) {
	Birthday = birthday;
}
public String getCulture() {
	return Culture;
}
public void setCulture(String culture) {
	Culture = culture;
}

public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
 
}
