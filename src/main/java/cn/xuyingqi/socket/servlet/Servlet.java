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
	 *            servlet配置
	 */
	public void init(ServletConfig servletConfig);

	/**
	 * 获取Servlet配置
	 * 
	 * @return
	 */
	public ServletConfig getServletConfig();

	/**
	 * Servlet服务
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 */
	public void service(ServletRequest request, ServletResponse response);

	/**
	 * 销毁Servlet
	 */
	public void destroy();

	/**
	 * 获取Servlet信息
	 * 
	 * @return
	 */
	public String getServletInfo();
}
