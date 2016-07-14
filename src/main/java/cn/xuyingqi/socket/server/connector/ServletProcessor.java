package cn.xuyingqi.socket.server.connector;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.servlet.Servlet;

/**
 * Servlet处理器
 * 
 * @author XuYQ
 *
 */
public class ServletProcessor {

	/**
	 * 日志
	 */
	private static final Logger logger = Logger.getLogger(ServletProcessor.class);

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
			// 打印日志
			logger.info("ServletClass加载成功");

			// 创建Socket请求外观类
			SocketRequestFacade servletRequest = new SocketRequestFacade(socketRequest);
			// 创建Socket响应外观类
			SocketResponseFacade servletResponse = new SocketResponseFacade(socketResponse);

			// 实例化Servlet
			Servlet servlet = servletClass.newInstance();
			// 打印日志
			logger.info("Servlet实例成功");

			// 调用Servlet服务方法
			servlet.service(servletRequest, servletResponse);

		} catch (ClassNotFoundException e) {

			// 打印日志
			logger.error("ServletClass未找到");

		} catch (InstantiationException e) {

			// 打印日志
			logger.error("ServletClass实例化失败");

		} catch (IllegalAccessException e) {

			// 打印日志
			logger.error("ServletClass实例化失败");
		}
	}
}
