package com.hzz.xkxt.bean;





public class Student {
   private String StudentID;
   private String StudentName;
   private String Sex;
   private String Birth;
   private String Photo;
   public String getPhoto() {
	return Photo;
}
public void setPhoto(String photo) {
	Photo = photo;
}
private Class01 Class01;
   public Class01 getClassmgr() {
	return Class01;
}
public void setClassmgr(Class01 Class01) {
	this.Class01 = Class01;
}
private String Password;
public String getSex() {
	return Sex;
}
public void setSex(String sex) {
	Sex = sex;
}
public String getBirth() {
	return Birth;
}
public void setBirth(String birth) {
	Birth = birth;
}

private String Remark;
public String getStudentID() {
	return StudentID;
}
public void setStudentID(String studentID) {
	StudentID = studentID;
}
public String getStudentName() {
	return StudentName;
}
public void setStudentName(String studentName) {
	StudentName = studentName;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
   
}
