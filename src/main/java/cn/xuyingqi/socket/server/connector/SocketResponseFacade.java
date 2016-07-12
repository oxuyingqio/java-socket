package cn.xuyingqi.socket.server.connector;

import java.io.OutputStream;

import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * Socket响应外观类,防止向下转型
 * 
 * @author XuYQ
 *
 */
public class SocketResponseFacade implements ServletResponse {

	/**
	 * Servlet响应
	 */
	private ServletResponse servletResponse;

	/**
	 * Socket响应外观类
	 * 
	 * @param socketResponse
	 *            Socket响应
	 */
	public SocketResponseFacade(SocketResponse socketResponse) {

		this.servletResponse = socketResponse;
	}

	@Override
	public OutputStream getOutputStream() {
		return this.servletResponse.getOutputStream();
	}
}
