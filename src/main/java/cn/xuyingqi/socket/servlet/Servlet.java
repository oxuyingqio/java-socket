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
	 * Servlet服务
	 * 
	 * @param request
	 * @param response
	 */
	public void service(ServletRequest request, ServletResponse response);

	/**
	 * 销毁Servlet
	 */
	public void destroy();

	/**
	 * 获取Servlet配置
	 * 
	 * @return
	 */
	public ServletConfig getServletConfig();

	/**
	 * 获取Servlet信息
	 * 
	 * @return
	 */
	public String getServletInfo();
}
