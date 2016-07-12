package cn.xuyingqi.socket.server.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket处理器
 * 
 * @author XuYQ
 *
 */
public class SocketProcessor {

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

			// 获取请求
			SocketRequest request = new SocketRequest(inputStream);
			// 获取响应
			SocketResponse response = new SocketResponse(outputStream);
			response.setRequest(request);

			// 获取Servlet处理器
			ServletProcessor servletProcessor = new ServletProcessor();
			// 处理请求响应
			servletProcessor.process(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
