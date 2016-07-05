package cn.xuyingqi.socket;

/**
 * Socket程序
 * 
 * @author XuYQ
 *
 */
public interface SocketServlet {

	/**
	 * 初始化程序
	 * 
	 * @param config
	 */
	public void init(SocketServletConfig config);

	/**
	 * 获取程序配置
	 * 
	 * @return
	 */
	public SocketServletConfig getSocketServletConfig();

	// public void service(ServletRequest req, ServletResponse res);
	// public String getServletInfo();
	// public void destroy();
}
