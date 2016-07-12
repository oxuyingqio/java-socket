package cn.xuyingqi.socket.server.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Socket处理器
 * 
 * @author XuYQ
 *
 */
public class SocketProcessor {

	/**
	 * 日志
	 */
	private static final Logger logger = Logger.getLogger(SocketProcessor.class);

	/**
	 * 处理客户端连接
	 * 
	 * @param socket
	 *            客户端连接
	 */
	public void process(Socket socket) {

		try {

			// 获取输入流
			InputStream inputStream = socket.getInputStream();
			// 获取输出流
			OutputStream outputStream = socket.getOutputStream();

			// 获取Socket请求
			SocketRequest socketRequest = new SocketRequest(inputStream);
			// 获取Socket响应
			SocketResponse socketResponse = new SocketResponse(outputStream);
			socketResponse.setSocketRequest(socketRequest);

			// 获取Servlet处理器
			ServletProcessor servletProcessor = new ServletProcessor();
			// 处理请求响应
			servletProcessor.process(socketRequest, socketResponse);

		} catch (IOException e) {

			// 打印日志
			logger.error("处理客户端连接异常");
		}
	}
}
