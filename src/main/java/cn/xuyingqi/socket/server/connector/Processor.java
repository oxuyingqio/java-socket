package cn.xuyingqi.socket.server.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.servlet.Servlet;

/**
 * 处理器
 * 
 * @author XuYQ
 *
 */
public class Processor {

	/**
	 * 日志
	 */
	private Logger logger = Logger.getLogger(Processor.class);

	/**
	 * 处理器
	 * 
	 * @param connector
	 *            连接器
	 */
	public Processor() {

	}

	/**
	 * 处理
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 */
	@SuppressWarnings("unchecked")
	public void process(Socket socket) {

		try {

			// 获取输入流
			InputStream inputStream = socket.getInputStream();
			// 获取输出流
			OutputStream outputStream = socket.getOutputStream();

			// 获取请求
			Request request = new Request(inputStream);
			// 获取响应
			Response response = new Response(outputStream);
			response.setRequest(request);

			// 加载ServletClass
			Class<Servlet> servletClass = (Class<Servlet>) ClassLoader.getSystemClassLoader()
					.loadClass("cn.xuyingqi.socket.servlet.impl.TestServlet");

			// 创建Request外观类
			RequestFacade servletRequest = new RequestFacade(request);
			// 创建Response外观类
			ResponseFacade servletResponse = new ResponseFacade(response);

			// 实例化Servlet
			Servlet servlet = servletClass.newInstance();

			// 调用Servlet服务方法,传入外观类,防止servlet向下转型
			servlet.service(servletRequest, servletResponse);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
