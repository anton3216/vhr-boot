package org.cskj.vhr.exception;

import java.sql.SQLException;

import org.cskj.vhr.utils.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SQLException.class)
	public ResponseBean sqlException(SQLException e) {
		if(e instanceof MySQLIntegrityConstraintViolationException) {
			return ResponseBean.error("该数据有关联数据,操作失败!");
		}
		return ResponseBean.error("数据或SQL异常,操作失败!",e);
	}
}
