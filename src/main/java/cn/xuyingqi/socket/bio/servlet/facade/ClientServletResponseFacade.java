package cn.xuyingqi.socket.bio.servlet.facade;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ClientServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;

/**
 * 客户端Servlet响应外观类
 * 
 * @author XuYQ
 *
 */
public class ClientServletResponseFacade implements ClientServletResponse {

	/**
	 * 客户端Servlet响应
	 */
	private ClientServletResponse response;

	/**
	 * 客户端Servlet响应外观类
	 * 
	 * @param response
	 *            客户端Servlet响应
	 */
	public ClientServletResponseFacade(ClientServletResponse response) {

		this.response = response;
	}

	@Override
	public ServletRequest getServletRequest() {

		return this.response.getServletRequest();
	}

	@Override
	public Datagram getDatagram() {

		return this.response.getDatagram();
	}
}
