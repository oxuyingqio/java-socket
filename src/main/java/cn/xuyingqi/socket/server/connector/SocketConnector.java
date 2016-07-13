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
public class SocketConnector implements Runnable {

	/**
	 * 日志
	 */
	private static final Logger logger = Logger.getLogger(SocketConnector.class);

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
	 * 初始化Socket连接器
	 */
	public void init() {

		try {

			// 创建Socket服务
			this.server = new ServerSocket();
			// 绑定主机,端口号
			this.server.bind(new InetSocketAddress(this.hostName, this.port));

			// 打印日志
			logger.info("Socket连接器(" + this.hostName + ":" + this.port + ")注册成功");

		} catch (IOException e) {

			// 打印日志
			logger.error("Socket连接器(" + this.hostName + ":" + this.port + ")注册失败");
		}
	}

	/**
	 * 启动Socket连接器
	 */
	public void start() {

		// 创建线程
		Thread thread = new Thread(this);
		// 启动线程
		thread.start();

		// 打印日志
		logger.info("Socket连接器(" + this.hostName + ":" + this.port + ")启动成功");
	}

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

				// 打印日志
				logger.error("客户端连接异常");
			}
		}
	}
}
