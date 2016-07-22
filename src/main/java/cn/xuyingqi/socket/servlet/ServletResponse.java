package cn.xuyingqi.socket.servlet;

import java.util.Set;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public interface ServletResponse {

	/**
	 * 获取Servlet会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession();

	/**
	 * 获取Servlet请求
	 * 
	 * @return
	 */
	public ServletRequest getServletRequest();

	/**
	 * 设置属性
	 * 
	 * @param name
	 *            属性名称
	 * @param object
	 *            属性值
	 */
	public void setAttribute(String name, Object object);

	/**
	 * 获取属性
	 * 
	 * @param name
	 *            属性名称
	 * @return
	 */
	public Object getAttribute(String name);

	/**
	 * 获取属性名称集合
	 * 
	 * @return
	 */
	public Set<String> getAttributeNames();
}
