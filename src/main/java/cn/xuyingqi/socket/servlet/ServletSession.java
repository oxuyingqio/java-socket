package cn.xuyingqi.socket.servlet;

import java.util.Set;

/**
 * Servlet会话
 * 
 * @author XuYQ
 *
 */
public interface ServletSession {

	/**
	 * 获取Servlet上下文
	 * 
	 * @return
	 */
	public ServletContext getServletContext();

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
