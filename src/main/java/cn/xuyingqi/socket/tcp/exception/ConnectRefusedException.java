package cn.xuyingqi.socket.tcp.exception;

/**
 * Socket连接被拒绝
 * 
 * @author XuYQ
 *
 */
public class ConnectRefusedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectRefusedException() {
		super("连接被拒绝");
	}

	public ConnectRefusedException(String message) {
		super(message);
	}

	public ConnectRefusedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectRefusedException(Throwable cause) {
		super(cause);
	}

	protected ConnectRefusedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
