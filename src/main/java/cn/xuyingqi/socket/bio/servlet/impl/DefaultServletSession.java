package cn.xuyingqi.socket.bio.servlet.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletSession;

/**
 * 默认的Servlet会话
 * 
 * @author XuYQ
 *
 */
public class DefaultServletSession extends AbstractServletSession {

	/**
	 * Servlet上下文
	 */
	private ServletContext context;

	/**
	 * 最后一次请求的时间
	 */
	private long lastAccessedTime;

	/**
	 * 本机地址
	 */
	private InetSocketAddress local;

	/**
	 * 远程地址
	 */
	private InetSocketAddress remote;

	/**
	 * 默认的Servlet会话
	 * 
	 * @param servletContext
	 *            Servlet上下文
	 * @param id
	 *            会话ID
	 * @param local
	 *            本机地址
	 * @param remote
	 *            远程地址
	 */
	public DefaultServletSession(String id, SocketAddress local, SocketAddress remote) {

		super(id);

		this.lastAccessedTime = this.getCreationTime();
		this.local = (InetSocketAddress) local;
		this.remote = (InetSocketAddress) remote;
	}

	@Override
	public ServletContext getServletContext() {

		return this.context;
	}

	/**
	 * 设置Servlet上下文
	 * 
	 * @param context
	 */
	public void setServletContext(ServletContext context) {

		this.context = context;
	}

	@Override
	public long getLastAccessedTime() {

		return this.lastAccessedTime;
	}

	/**
	 * 更新最后一次请求时间
	 * 
	 * @return
	 */
	public DefaultServletSession updateLastAccessedTime() {

		this.lastAccessedTime = System.currentTimeMillis();

		return this;
	}

	@Override
	public InetAddress getLocalAddr() {

		return this.local.getAddress();
	}

	@Override
	public String getLocalHost() {

		return this.local.getHostName();
	}

	@Override
	public int getLocalPort() {

		return this.local.getPort();
	}

	@Override
	public String getProtocol() {

		// 未实现,不影响使用

		return "";
	}

	@Override
	public InetAddress getRemoteAddr() {

		return this.remote.getAddress();
	}

	@Override
	public String getRemoteHost() {

		return this.remote.getHostName();
	}

	@Override
	public int getRemotePort() {

		return this.remote.getPort();
	}
}
