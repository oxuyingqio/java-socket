package cn.xuyingqi.socket.servlet;

import java.io.InputStream;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public interface ServletRequest {

	/**
	 * 获取Servlet会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession();

	/**
	 * 获取请求输入流
	 * 
	 * @return
	 */
	public InputStream getInputStream();
}