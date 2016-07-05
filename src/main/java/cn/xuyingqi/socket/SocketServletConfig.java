package cn.xuyingqi.socket;

/**
 * Socket程序配置
 * 
 * @author XuYQ
 *
 */
public interface SocketServletConfig {

	/**
	 * 获取程序上下文
	 * 
	 * @return
	 */
	public SocketServletContext getSocketServletContext();

	// public String getServletName();
	// public String getInitParameter(String name);
	// public Enumeration getInitParameterNames();
}