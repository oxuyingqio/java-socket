package cn.xuyingqi.socket.bio.servlet.impl;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletResponse;

/**
 * 默认的服务器端Servlet响应
 * 
 * @author XuYQ
 *
 */
public class DefaultServerServletResponse extends AbstractServerServletResponse {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的服务器端Servlet响应
	 * 
	 * @param servletRequest
	 *            servlet请求
	 */
	public DefaultServerServletResponse(ServletRequest servletRequest) {

		super(servletRequest);
	}

	@Override
	public ServletResponse setDatagram(Datagram datagram) {

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
