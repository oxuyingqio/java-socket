package cn.xuyingqi.socket.servlet;

import java.util.Set;

/**
 * 抽象公共Servlet会话
 * 
 * @author XuYQ
 *
 */
public class AbstractServletSession implements ServletSession {
	
	public AbstractServletSession() {

	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public void setAttribute(String name, Object object) {

	}

	@Override
	public Object getAttribute(String name) {
		return null;
	}

	@Override
	public Set<String> getAttributeNames() {
		return null;
	}
}
