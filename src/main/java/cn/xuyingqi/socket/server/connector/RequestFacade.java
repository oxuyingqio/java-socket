package cn.xuyingqi.socket.server.connector;

import java.io.InputStream;

import cn.xuyingqi.socket.servlet.ServletRequest;

/**
 * Request外观类
 * 
 * @author XuYQ
 *
 */
public class RequestFacade implements ServletRequest {

	/**
	 * Servlet请求
	 */
	private ServletRequest servletRequest;

	/**
	 * Request外观类
	 * 
	 * @param request
	 *            请求
	 */
	public RequestFacade(Request request) {

		this.servletRequest = request;
	}

	@Override
	public InputStream getInputStream() {
		return this.servletRequest.getInputStream();
	}
}
