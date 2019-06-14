package cn.edu.hpu.autoweb.exception;

/**
 * 封装访问资源时发生的异常
 * 
 * @version 1.0 2011-09-06
 * @since   1.0
 */
public class ResourceReadException extends RuntimeException {

	private static final long serialVersionUID = -609371179447372122L;

	public ResourceReadException() {
		
	}

	public ResourceReadException(String message) {
		super(message);
	}

	public ResourceReadException(Throwable cause) {
		super(cause);
	}

	public ResourceReadException(String message, Throwable cause) {
		super(message, cause);
	}

}
