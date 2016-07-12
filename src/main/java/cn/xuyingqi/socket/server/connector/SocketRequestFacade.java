package cn.xuyingqi.socket.server.connector;

import java.io.InputStream;

import cn.xuyingqi.socket.servlet.ServletRequest;

/**
 * Socket请求外观类,防止向下转型
 * 
 * @author XuYQ
 *
 */
public class SocketRequestFacade implements ServletRequest {

	/**
	 * Servlet请求
	 */
	private ServletRequest servletRequest;

	/**
	 * Socket请求外观类
	 * 
	 * @param socketRequest
	 *            Socket请求
	 */
	public SocketRequestFacade(SocketRequest socketRequest) {

		this.servletRequest = socketRequest;
	}

	@Override
	public InputStream getInputStream() {
		return this.servletRequest.getInputStream();
	}
}
