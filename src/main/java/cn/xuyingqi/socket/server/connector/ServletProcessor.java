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
	 * 处理客户端请求响应
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 */
	@SuppressWarnings("unchecked")
	public void process(SocketRequest request, SocketResponse response) {

		try {

			// 加载ServletClass
			Class<Servlet> servletClass = (Class<Servlet>) ClassLoader.getSystemClassLoader()
					.loadClass("cn.xuyingqi.socket.servlet.impl.TestServlet");

			// 创建Request外观类
			SocketRequestFacade servletRequest = new SocketRequestFacade(request);
			// 创建Response外观类
			SocketResponseFacade servletResponse = new SocketResponseFacade(response);

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
