package org.cskj.vhr.bean;

import java.util.Objects;

public class Nation {
	private Integer id;

	private String name;

	public Nation(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Nation nation = (Nation) obj;
		return Objects.equals(name, nation.name);
	}

	public Nation() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}