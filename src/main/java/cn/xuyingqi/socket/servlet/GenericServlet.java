package cn.xuyingqi.socket.servlet;

import java.io.Serializable;

/**
 * 一般化的Servlet
 * 
 * @author XuYQ
 *
 */
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet配置
	 */
	private transient ServletConfig servletConfig;

	/**
	 * 一般化的Servlet
	 */
	public GenericServlet() {

	}

	@Override
	public ServletContext getServletContext() {

		ServletConfig sc = getServletConfig();

		return sc.getServletContext();
	}

	@Override
	public void init(ServletConfig servletConfig) {

		this.servletConfig = servletConfig;
	}

	@Override
	public ServletConfig getServletConfig() {

		return servletConfig;
	}

	@Override
	public void destroy() {

	}
}
