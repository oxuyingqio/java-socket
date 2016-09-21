package cn.xuyingqi.socket.bio.servlet.facade;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.socket.bio.servlet.impl.DefaultServerServletResponse;

/**
 * 服务器端Servlet响应外观类
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponseFacade implements ServerServletResponse {

	/**
	 * 服务器端Servlet响应
	 */
	private ServerServletResponse response;

	/**
	 * 服务器端Servlet响应外观类
	 * 
	 * @param response
	 *            Servlet响应
	 */
	public ServerServletResponseFacade(DefaultServerServletResponse response) {

		this.response = response;
	}

	@Override
	public ServletRequest getServletRequest() {

		return this.response.getServletRequest();
	}

	@Override
	public ServletResponse setDatagram(Datagram datagram) {

		return this.response.setDatagram(datagram);
	}
}
