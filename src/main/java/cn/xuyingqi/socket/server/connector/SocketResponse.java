package cn.xuyingqi.socket.server.connector;

import java.io.OutputStream;

import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * 响应
 * 
 * @author XuYQ
 *
 */
public class SocketResponse implements ServletResponse {

	/**
	 * 请求
	 */
	private SocketRequest request;
	/**
	 * 输出流
	 */
	private OutputStream outputStream;

	/**
	 * 响应
	 * 
	 * @param outputStream
	 *            输出流
	 */
	public SocketResponse(OutputStream outputStream) {

		this.outputStream = outputStream;
	}

	/**
	 * 获取请求
	 * 
	 * @return
	 */
	public SocketRequest getRequest() {
		return request;
	}

	/**
	 * 设置请求
	 * 
	 * @param request
	 */
	public void setRequest(SocketRequest request) {
		this.request = request;
	}

	/**
	 * 获取输出流
	 * 
	 * @return
	 */
	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}
}
