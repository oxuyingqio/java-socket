package cn.xuyingqi.socket.servlet.impl;

import cn.xuyingqi.socket.servlet.GenericServlet;
import cn.xuyingqi.socket.servlet.ServletContext;

/**
 * 测试Servlet
 * 
 * @author XuYQ
 *
 */
public class TestServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext servletContext = new TestServletContext();

	public TestServlet() {

	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}
}
