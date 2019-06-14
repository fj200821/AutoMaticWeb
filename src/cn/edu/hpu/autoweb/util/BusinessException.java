package cn.edu.hpu.autoweb.util;

/**
 * 异常处理
 * @author 赵宝旗
 */
public class BusinessException extends Exception {

	String errorMessage;

	public BusinessException(Exception e, String errorMessage) {
		super(e);
		setErrorMessage(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
