package cn.xuyingqi.socket.server.connector;

import cn.xuyingqi.socket.servlet.Servlet;

/**
 * Servlet处理器
 * 
 * @author XuYQ
 *
 */
public class ServletProcessor {

	/**
	 * 处理请求响应
	 * 
	 * @param socketRequest
	 *            Socket请求
	 * @param socketResponse
	 *            Socket响应
	 */
	@SuppressWarnings("unchecked")
	public void process(SocketRequest socketRequest, SocketResponse socketResponse) {

		try {

			// 加载Servlet类
			Class<Servlet> servletClass = (Class<Servlet>) ClassLoader.getSystemClassLoader()
					.loadClass("cn.xuyingqi.socket.servlet.impl.TestServlet");

			// 创建Socket请求外观类
			SocketRequestFacade servletRequest = new SocketRequestFacade(socketRequest);
			// 创建Socket响应外观类
			SocketResponseFacade servletResponse = new SocketResponseFacade(socketResponse);

			// 实例化Servlet
			Servlet servlet = servletClass.newInstance();

			// 调用Servlet服务方法
			servlet.service(servletRequest, servletResponse);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
