package org.cskj.vhr.bean;

public class Dept {

	private Department parentDept;
	private Department childrenDept;

	public Department getParentDept() {
		return parentDept;
	}

	public void setParentDept(Department parentDept) {
		this.parentDept = parentDept;
	}

	public Department getChildrenDept() {
		return childrenDept;
	}

	public void setChildrenDept(Department childrenDept) {
		this.childrenDept = childrenDept;
	}

}
