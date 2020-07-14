package com.hzz.xkxt.bean;

public class Course {
 private String CourseID;
 private String CourseName;
 private Profession Profession;
 private String CurriculumTime;
 private String StudyTime;
 private String Crediy;
 private String Auditing;
 private String State;
 
 public String getAuditing() {
	return Auditing;
}
public void setAuditing(String auditing) {
	Auditing = auditing;
}
public String getStudyTime() {
	return StudyTime;
}
public void setStudyTime(String studyTime) {
	StudyTime = studyTime;
}
public String getCrediy() {
	return Crediy;
}
public void setCrediy(String crediy) {
	Crediy = crediy;
}
private String Remark;
 public String getCourseID() {
	return CourseID;
}
public void setCourseID(String courseID) {
	CourseID = courseID;
}
public String getCourseName() {
	return CourseName;
}
public void setCourseName(String courseName) {
	CourseName = courseName;
}

public Profession getProfession() {
	return Profession;
}
public void setProfession(Profession Profession) {
	this.Profession = Profession;
}
public String getCurriculumTime() {
	return CurriculumTime;
}
public void setCurriculumTime(String curriculumTime) {
	CurriculumTime = curriculumTime;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}


}
