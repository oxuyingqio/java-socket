package cn.xuyingqi.socket.servlet;

/**
 * Servlet
 * 
 * @author XuYQ
 *
 */
public interface Servlet {

	/**
	 * 初始化Servlet
	 * 
	 * @param servletConfig
	 */
	public void init(ServletConfig servletConfig);

	/**
	 * 获取Servlet配置
	 * 
	 * @return
	 */
	public ServletConfig getServletConfig();

	/**
	 * 销毁Servlet
	 */
	public void destroy();
}
