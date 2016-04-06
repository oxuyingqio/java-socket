package cn.xuyingqi.socket.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.server.impl.SocketByteMsgHandler;

/**
 * 服务器
 * 
 * @author Administrator
 *
 */
public class Server {
	// 日志
	private Logger logger = Logger.getLogger(Server.class);

	// 默认线程池大小
	private static final int DEFAULT_THREAD_POOL_SIZE = 10;

	// 端口号
	private int port;
	// 最大连接数
	private int maxConnection;
	// IP地址
	private String host;

	// 线程池大小
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;

	// 服务套接字
	private ServerSocket serverSocket;

	// 线程池执行者
	private ExecutorService executor;

	// 套接字消息处理器
	private SocketMsgHandler socketMsgHandler;

	/**
	 * 服务器
	 * 
	 * @param port
	 *            端口号
	 * @throws IOException
	 */
	public Server(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
		this.init();
	}

	/**
	 * 服务器
	 * 
	 * @param port
	 *            端口号
	 * @param maxConnection
	 *            最大连接数
	 * @throws IOException
	 */
	public Server(int port, int maxConnection) throws IOException {
		this.port = port;
		this.maxConnection = maxConnection;
		this.serverSocket = new ServerSocket(this.port, this.maxConnection);
		this.init();
	}

	/**
	 * 服务器
	 * 
	 * @param port
	 *            端口号
	 * @param maxConnection
	 *            最大连接数
	 * @param host
	 *            绑定IP
	 * @throws IOException
	 */
	public Server(int port, int maxConnection, String host) throws IOException {
		this.port = port;
		this.maxConnection = maxConnection;
		this.host = host;
		this.serverSocket = new ServerSocket(this.port, this.maxConnection, InetAddress.getByName(this.host));
		this.init();
	}

	/**
	 * 初始化参数
	 */
	private void init() {
		// 创建线程池
		this.executor = Executors.newFixedThreadPool(this.threadPoolSize);
		// 套接字消息处理器
		this.socketMsgHandler = new SocketByteMsgHandler();
		// 日志
		this.logger.info("服务已注册");
	}

	/**
	 * 设置线程池大小
	 * 
	 * @param threadPoolSize
	 */
	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
		this.executor = Executors.newFixedThreadPool(this.threadPoolSize);
	}

	/**
	 * 设置套接字消息消息处理器
	 * 
	 * @param socketMsgHandler
	 */
	public void setSocketMsgHandler(SocketMsgHandler socketMsgHandler) {
		this.socketMsgHandler = socketMsgHandler;
	}

	/**
	 * 启动服务,等待请求
	 */
	public void activate() {
		while (true) {
			try {
				// 获取套接字
				Socket socket = this.serverSocket.accept();
				this.logger.info("客户端(" + socket.getInetAddress() + ":" + socket.getPort() + ")已连接");

				// 向线程池中添加新的处理套接字线程
				executor.execute(new SocketThread(socket, this.socketMsgHandler));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
