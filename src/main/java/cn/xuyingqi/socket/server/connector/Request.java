package cn.xuyingqi.socket.server.connector;

import java.io.InputStream;

import cn.xuyingqi.socket.servlet.ServletRequest;

/**
 * 请求
 * 
 * @author XuYQ
 *
 */
public class Request implements ServletRequest {

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 请求
	 * 
	 * @param inputStream
	 *            输入流
	 */
	public Request(InputStream inputStream) {

		this.inputStream = inputStream;
	}

	/**
	 * 获取输入流
	 * 
	 * @return
	 */
	@Override
	public InputStream getInputStream() {
		return inputStream;
	}
}
