package cn.xuyingqi.socket.servlet;

/**
 * 抽象公共Servlet配置
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractServletConfig implements ServletConfig {

	/**
	 * Servlet上下文
	 */
	private ServletContext servletContext;

	/**
	 * 抽象公共Servlet配置
	 * 
	 * @param servletContext
	 */
	public AbstractServletConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public ServletContext getServletContext() {
		return this.servletContext;
	}
}
