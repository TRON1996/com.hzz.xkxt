package com.hzz.xkxt.bean;

public class Profession {
   private String  ProfessionID;
   private String ProfessionName;
   private Department Department;
   private String Remark;
public String getProfessionID() {
	return ProfessionID;
}
public void setProfessionID(String professionID) {
	ProfessionID = professionID;
}
public String getProfessionName() {
	return ProfessionName;
}
public void setProfessionName(String professionName) {
	ProfessionName = professionName;
}

public Department getDepartment() {
	return Department;
}
public void setDepartment(Department Department) {
	this.Department = Department;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
   
   
}
