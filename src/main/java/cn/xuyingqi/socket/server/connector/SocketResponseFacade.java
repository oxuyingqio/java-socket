package cn.xuyingqi.socket.server.connector;

import java.io.OutputStream;

import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * Response外观类,防止向下转型
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
	 * Response外观类
	 * 
	 * @param response
	 *            响应
	 */
	public SocketResponseFacade(SocketResponse response) {

		this.servletResponse = response;
	}

	@Override
	public OutputStream getOutputStream() {
		return this.servletResponse.getOutputStream();
	}
}
