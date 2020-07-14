package com.hzz.xkxt.bean;

public class Audit {
   private String ID;
   private String TeachTaskID;
   private String AuditDate;
   private Admin admin;
   private String Auditing;
   private String CourseName;
   private String Remark;
   private String TeacherID;
   private String TeacherName;
   private String CourseID;
 

public String getCourseName() {
	return CourseName;
}
public void setCourseName(String courseName) {
	CourseName = courseName;
}

public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getTeachTaskID() {
	return TeachTaskID;
}
public void setTeachTaskID(String teachTaskID) {
	TeachTaskID = teachTaskID;
}
public String getAuditDate() {
	return AuditDate;
}
public void setAuditDate(String auditDate) {
	AuditDate = auditDate;
}

public String getAuditing() {
	return Auditing;
}
public void setAuditing(String auditing) {
	Auditing = auditing;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
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
public String getCourseID() {
	return CourseID;
}
public void setCourseID(String courseID) {
	CourseID = courseID;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}


}
