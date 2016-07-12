package cn.xuyingqi.socket.server.connector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Socket连接器
 * 
 * @author XuYQ
 *
 */
public class SocketConnector {

	/**
	 * 日志
	 */
	private Logger logger = Logger.getLogger(SocketConnector.class);

	/**
	 * 默认主机名
	 */
	private static final String HOST_NAME = "127.0.0.1";
	/**
	 * 默认端口号
	 */
	private static final int PORT = 60000;

	/**
	 * Socket服务
	 */
	private ServerSocket server;

	/**
	 * 主机名
	 */
	private String hostName = HOST_NAME;

	/**
	 * 端口号
	 */
	private int port = PORT;

	/**
	 * Socket服务线程
	 */
	private Thread serverThread;

	/**
	 * 初始化Socket连接器
	 */
	public void init() {

		try {

			// 创建Socket服务
			this.server = new ServerSocket();
			// 绑定主机,端口号
			this.server.bind(new InetSocketAddress(this.hostName, this.port));

			// 设置Socket服务线程
			this.serverThread = new Thread(new ServerSocketThread());

			// 打印日志
			this.logger.info("Socket连接器(" + this.hostName + ":" + this.port + ")注册成功");

		} catch (IOException e) {

			// 打印日志
			this.logger.error("Socket连接器(" + this.hostName + ":" + this.port + ")注册失败");
		}
	}

	/**
	 * 激活Socket连接器
	 */
	public void activate() {

		// 启动Socket服务线程
		this.serverThread.start();

		// 打印日志
		this.logger.info("Socket连接器(" + this.hostName + ":" + this.port + ")启动成功");
	}

	/**
	 * Socket服务线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class ServerSocketThread implements Runnable {

		@Override
		public void run() {

			// 监听客户端连接
			while (true) {

				try {

					// 客户端
					Socket client = server.accept();

					// 获取Socket处理器
					SocketProcessor socketProcessor = new SocketProcessor();
					// 处理客户端连接
					socketProcessor.process(client);

					// 关闭客户端连接
					client.close();

				} catch (IOException e) {

					logger.error("客户端连接异常");
				}
			}
		}
	}
}
