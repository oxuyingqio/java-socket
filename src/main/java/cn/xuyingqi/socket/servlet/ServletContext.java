package cn.xuyingqi.socket.servlet;

/**
 * Servlet上下文
 * 
 * @author XuYQ
 *
 */
public interface ServletContext {

	/**
	 * 设置属性
	 * 
	 * @param name
	 * @param object
	 */
	public void setAttribute(String name, Object object);

	/**
	 * 获取属性
	 * 
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name);
}
