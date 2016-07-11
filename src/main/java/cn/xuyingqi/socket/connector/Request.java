package cn.xuyingqi.socket.connector;

import java.io.InputStream;

/**
 * 请求
 * 
 * @author XuYQ
 *
 */
public class Request {

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 请求
	 * 
	 * @param inputStream
	 */
	public Request(InputStream inputStream) {

		this.inputStream = inputStream;
	}

	/**
	 * 获取输入流
	 * 
	 * @return
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
}
