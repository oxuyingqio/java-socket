package cn.xuyingqi.socket.server.connector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.net.ServerSocketFactory;

import org.apache.log4j.Logger;

import cn.xuyingqi.util.util.ListFactory;

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
	 * 默认最小Socket处理器个数
	 */
	private static final int MIN_PROCESSORS = 5;
	/**
	 * 默认最大Socket处理器个数
	 */
	private static final int MAX_PROCESSORS = 20;

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
	 * Socket处理器集合
	 */
	private List<SocketProcessor> proccessors = ListFactory.newInstance();

	/**
	 * 最小Socket处理器个数
	 */
	private int minProcessors = MIN_PROCESSORS;
	/**
	 * 最大Socket处理器个数
	 */
	private int maxProcessors = MAX_PROCESSORS;
	/**
	 * 当前Socket处理器个数
	 */
	private int curProcessors = 0;

	/**
	 * 初始化Socket连接器
	 */
	public void init() {

		// 创建Socket服务
		this.server = this.open();
	}

	/**
	 * 创建Socket服务
	 * 
	 * @return
	 */
	private ServerSocket open() {

		try {

			// 创建Socket服务
			ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket();
			// 绑定主机,端口号
			serverSocket.bind(new InetSocketAddress(this.hostName, this.port));

			// 打印日志
			logger.info("Socket连接器(" + this.hostName + ":" + this.port + ")注册成功");

			// 返回Socket服务
			return serverSocket;

		} catch (IOException e) {

			// 打印日志
			logger.error("Socket连接器(" + this.hostName + ":" + this.port + ")注册失败");

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 启动Socket连接器
	 */
	public void start() {

		// 创建Socket处理器
		while (this.curProcessors < this.minProcessors) {
			
		}

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
				SocketProcessor socketProcessor = new SocketProcessor(this);
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
