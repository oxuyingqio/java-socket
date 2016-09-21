package cn.xuyingqi.socket.bio.servlet.impl;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ClientServletRequest;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractClientServletRequest;

/**
 * 默认的客户端Servlet请求
 * 
 * @author XuYQ
 *
 */
public class DefaultClientServletRequest extends AbstractClientServletRequest {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的客户端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 */
	public DefaultClientServletRequest(ServletSession servletSession) {

		super(servletSession);
	}

	@Override
	public ClientServletRequest setDatagram(Datagram datagram) {

		this.datagram = datagram;

		return this;
	}

	/**
	 * 返回数据报文对象
	 * 
	 * @return
	 */
	public Datagram getDatagram() {

		return this.datagram;
	}
}
