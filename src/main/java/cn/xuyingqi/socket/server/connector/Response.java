package cn.xuyingqi.socket.server.connector;

import java.io.OutputStream;

import cn.xuyingqi.socket.servlet.ServletResponse;

/**
 * 响应
 * 
 * @author XuYQ
 *
 */
public class Response implements ServletResponse {

	/**
	 * 请求
	 */
	private Request request;
	/**
	 * 输出流
	 */
	private OutputStream outputStream;

	/**
	 * 响应
	 * 
	 * @param outputStream
	 *            输出流
	 */
	public Response(OutputStream outputStream) {

		this.outputStream = outputStream;
		try {
			this.outputStream.write("123123123213".getBytes("GBK"));
		} catch (Exception e) {

		}
	}

	/**
	 * 获取请求
	 * 
	 * @return
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * 设置请求
	 * 
	 * @param request
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * 获取输出流
	 * 
	 * @return
	 */
	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}
}
