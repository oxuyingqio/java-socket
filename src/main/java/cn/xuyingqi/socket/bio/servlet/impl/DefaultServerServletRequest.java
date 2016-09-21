package cn.xuyingqi.socket.bio.servlet.impl;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletRequest;

/**
 * 默认的服务器端Servlet请求
 * 
 * @author XuYQ
 *
 */
public class DefaultServerServletRequest extends AbstractServerServletRequest {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的服务器端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 * @param datagram
	 *            数据报文
	 */
	public DefaultServerServletRequest(ServletSession servletSession, Datagram datagram) {

		super(servletSession);

		this.datagram = datagram;
	}

	@Override
	public Datagram getDatagram() {

		return this.datagram;
	}
}
