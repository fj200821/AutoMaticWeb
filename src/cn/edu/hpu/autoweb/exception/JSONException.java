package cn.edu.hpu.autoweb.exception;

/**
 * 封装处理JSON格式转换时发生的异常
 * 
 * @version 1.0 2012-07-05
 * @since   1.0
 */
public class JSONException extends RuntimeException {

	private static final long serialVersionUID = -1540383474211218352L;

	public JSONException() {
		// TODO Auto-generated constructor stub
	}

	public JSONException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public JSONException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public JSONException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
