package org.cskj.vhr.utils;

import java.util.List;

public class ResponsePageBean {

	private Long total;
	private List<? extends Object> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

	public ResponsePageBean(Long total, List<? extends Object> data) {
		super();
		this.total = total;
		this.data = data;
	}

	public ResponsePageBean() {
		super();
	}

}
