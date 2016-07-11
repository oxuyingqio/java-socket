package cn.xuyingqi.socket.servlet.impl;

import cn.xuyingqi.socket.servlet.Servlet;
import cn.xuyingqi.socket.servlet.ServletConfig;
import cn.xuyingqi.socket.servlet.ServletRequest;
import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * 测试Servlet
 * 
 * @author XuYQ
 *
 */
public class TestServlet implements Servlet {

	@Override
	public void init(ServletConfig servletConfig) {

	}

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		System.out.println("来了");
	}

	@Override
	public void destroy() {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}
}
