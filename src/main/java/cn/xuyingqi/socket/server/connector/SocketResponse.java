package cn.xuyingqi.socket.server.connector;

import java.io.OutputStream;

import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * Socket响应
 * 
 * @author XuYQ
 *
 */
public class SocketResponse implements ServletResponse {

	/**
	 * Socket请求
	 */
	private SocketRequest socketRequest;
	/**
	 * 输出流
	 */
	private OutputStream outputStream;

	/**
	 * Socket响应
	 * 
	 * @param outputStream
	 *            输出流
	 */
	public SocketResponse(OutputStream outputStream) {

		this.outputStream = outputStream;
	}

	/**
	 * 获取Socket请求
	 * 
	 * @return
	 */
	public SocketRequest getSocketRequest() {
		return socketRequest;
	}

	/**
	 * 设置Socket请求
	 * 
	 * @param request
	 */
	public void setSocketRequest(SocketRequest socketRequest) {
		this.socketRequest = socketRequest;
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
