package org.cskj.vhr.utils;

import org.cskj.vhr.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtil {

	public static Hr getCurrentHr() {
		return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
