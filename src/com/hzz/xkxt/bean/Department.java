 package com.hzz.xkxt.bean;

public class Department {
     private String departmentID;
     private String departmentName;
     private String Remark;
     private College College;
	
	public String getDepartmentID() {
		return departmentID;
	}
	public College getCollege() {
		return College;
	}
	public void setCollege(College College) {
		this.College = College;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}

     
}
