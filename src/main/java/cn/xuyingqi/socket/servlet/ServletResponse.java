package cn.xuyingqi.socket.servlet;

import java.io.OutputStream;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public interface ServletResponse {

	/**
	 * 获取响应输出流
	 * 
	 * @return
	 */
	public OutputStream getOutputStream();
}
