package cn.xuyingqi.socket.bio.servlet.impl;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.impl.AbstractClientServletResponse;

/**
 * 默认的客户端Servlet响应
 * 
 * @author XuYQ
 *
 */
public class DefaultClientServletResponse extends AbstractClientServletResponse {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的客户端Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 * @param datagram
	 *            数据报文
	 */
	public DefaultClientServletResponse(ServletRequest servletRequest, Datagram datagram) {

		super(servletRequest);

		this.datagram = datagram;
	}

	@Override
	public Datagram getDatagram() {

		return this.datagram;
	}
}
