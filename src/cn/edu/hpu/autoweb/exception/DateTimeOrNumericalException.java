package cn.edu.hpu.autoweb.exception;

/**
 * 封装处理日期、时间、数值类型时发生的异常
 * 
 * @version 1.0 2011-09-07
 * @since   1.0
 */
public class DateTimeOrNumericalException extends RuntimeException {

	private static final long serialVersionUID = -3624251924460196569L;

	public DateTimeOrNumericalException() {
		
	}

	public DateTimeOrNumericalException(String message) {
		super(message);
	}

	public DateTimeOrNumericalException(Throwable cause) {
		super(cause);
	}

	public DateTimeOrNumericalException(String message, Throwable cause) {
		super(message, cause);
	}

}
