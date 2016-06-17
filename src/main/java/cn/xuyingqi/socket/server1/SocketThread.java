package cn.xuyingqi.socket.server1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * 套接字连接线程
 * 
 * @author Administrator
 *
 */
public class SocketThread implements Runnable {
	// 日志
	private Logger logger = Logger.getLogger(SocketThread.class);

	// 套接字
	private Socket socket;
	// 字节输入流
	private InputStream is;
	// 字节输出流
	private OutputStream os;

	// 套接字消息处理器
	private SocketMsgHandler socketMsgHandler;

	/**
	 * 服务器套接字线程
	 * 
	 * @param socket
	 *            套接字
	 * @throws IOException
	 */
	public SocketThread(Socket socket, SocketMsgHandler socketMsgHandler) throws IOException {
		// 套接字
		this.socket = socket;
		// 获取字节输入流
		this.is = this.socket.getInputStream();
		// 获取字节输出流
		this.os = this.socket.getOutputStream();
		// 套接字消息处理器
		this.socketMsgHandler = socketMsgHandler;
	}

	@Override
	public void run() {
		// 获取客户端信息
		InetAddress inetAddress = this.socket.getInetAddress();
		int port = this.socket.getPort();

		// 处理输入/输出流
		this.socketMsgHandler.handleMsg(inetAddress + ":" + port, this.is, this.os);

		try {
			// 关闭字节输入流
			this.is.close();
			// 关闭字节输出流
			this.os.close();
			// 关闭套接字
			this.socket.close();
			// 日志
			this.logger.info("客户端(" + inetAddress + ":" + port + ")已断开");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
