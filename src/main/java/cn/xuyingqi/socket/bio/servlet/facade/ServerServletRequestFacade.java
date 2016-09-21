package cn.xuyingqi.socket.bio.servlet.facade;

import java.net.InetAddress;
import java.util.Set;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletSession;

/**
 * 服务器端Servlet请求外观类
 * 
 * @author XuYQ
 *
 */
public class ServerServletRequestFacade implements ServerServletRequest {

	/**
	 * 服务器端Servlet请求
	 */
	private ServerServletRequest request;

	/**
	 * 服务器端Servlet请求外观类
	 * 
	 * @param request
	 *            Servlet请求
	 */
	public ServerServletRequestFacade(ServerServletRequest request) {

		this.request = request;
	}

	@Override
	public ServletSession getServletSession() {

		return this.request.getServletSession();
	}

	@Override
	public String getServletSessionId() {

		return this.request.getServletSessionId();
	}

	@Override
	public InetAddress getLocalAddr() {

		return this.request.getLocalAddr();
	}

	@Override
	public String getLocalHost() {

		return this.request.getLocalHost();
	}

	@Override
	public int getLocalPort() {

		return this.request.getLocalPort();
	}

	@Override
	public String getProtocol() {

		return this.request.getProtocol();
	}

	@Override
	public InetAddress getRemoteAddr() {

		return this.request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {

		return this.request.getRemoteHost();
	}

	@Override
	public int getRemotePort() {

		return this.request.getRemotePort();
	}

	@Override
	public Datagram getDatagram() {

		return this.request.getDatagram();
	}

	@Override
	public Set<String> getAttributeNames() {

		return this.request.getAttributeNames();
	}

	@Override
	public Object getAttribute(String name) {

		return this.request.getAttribute(name);
	}

	@Override
	public ServletRequest removeAttribute(String name) {

		return this.request.removeAttribute(name);
	}

	@Override
	public ServletRequest setAttribute(String name, Object object) {

		return this.request.setAttribute(name, object);
	}
}
