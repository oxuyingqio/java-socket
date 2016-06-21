package cn.xuyingqi.socket.tcp.server.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP服务器
 * 
 * @author XuYQ
 *
 */
public class TCPServer implements cn.xuyingqi.socket.tcp.server.TCPServer {

	// 默认的主机名称
	private static final String DEFAULT_HOST_NAME = "127.0.0.1";
	// 默认的端口号
	private static final int DEFAULT_PORT = 60000;
	// 默认的线程池大小
	private static final int DEFAULT_THREAD_POOL_SIZE = 10;

	// 日志
	private Logger logger = Logger.getLogger(cn.xuyingqi.socket.tcp.server.TCPServer.class);

	// 连接时间性能
	private Integer connectionTime;
	// 延迟性能
	private Integer latency;
	// 带宽性能
	private Integer bandwidth;

	// 主机名称
	private String hostName = DEFAULT_HOST_NAME;
	// 端口号
	private int port = DEFAULT_PORT;
	// 线程池大小
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;

	// 服务器Socket
	private ServerSocket server;
	// 线程池调度者
	private ExecutorService executor;

	// 客户端超时时间
	private Integer clientTimeout;
	// 客户端活跃检测
	private Boolean clientKeepAlive;
	// 客户端消除缓冲延迟
	private Boolean clientTcpNoDelay;

	// 阻塞式TCP协议
	private TCPProtocol tcpProtocol;

	/**
	 * 阻塞式TCP服务器
	 * 
	 * @param tcpProtocol
	 *            阻塞式TCP协议
	 */
	public TCPServer(TCPProtocol tcpProtocol) {

		this.tcpProtocol = tcpProtocol;
	}

	/**
	 * 设置Socket服务性能
	 * 
	 * @param connectionTime
	 *            连接时间性能
	 * @param latency
	 *            延迟性能
	 * @param bandwidth
	 *            带宽性能
	 */
	public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {

		this.connectionTime = connectionTime;
		this.latency = latency;
		this.bandwidth = bandwidth;
	}

	@Override
	public String getHostName() {

		return hostName;
	}

	@Override
	public void setHostName(String hostName) {

		this.hostName = hostName;
	}

	@Override
	public int getPort() {

		return port;
	}

	@Override
	public void setPort(int port) {

		this.port = port;
	}

	/**
	 * 获取线程池大小
	 * 
	 * @return
	 */
	public int getThreadPoolSize() {

		return threadPoolSize;
	}

	/**
	 * 设置线程池大小
	 * 
	 * @param threadPoolSize
	 */
	public void setThreadPoolSize(int threadPoolSize) {

		this.threadPoolSize = threadPoolSize;
	}

	/**
	 * 设置客户端超时时间
	 * 
	 * @param clientTimeout
	 */
	public void setClientTimeout(int clientTimeout) {

		this.clientTimeout = clientTimeout;
	}

	/**
	 * 设置客户端活跃度检测
	 * 
	 * @param clientKeepAlive
	 */
	public void setClientKeepAlive(boolean clientKeepAlive) {

		this.clientKeepAlive = clientKeepAlive;
	}

	/**
	 * 设置客户端消除缓冲延迟
	 * 
	 * @param clientTcpNoDelay
	 */
	public void setClientTcpNoDelay(Boolean clientTcpNoDelay) {

		this.clientTcpNoDelay = clientTcpNoDelay;
	}

	@Override
	public ServerSocket init() throws IOException {

		// 创建Socket服务
		this.server = new ServerSocket();
		// 设置Socket服务性能
		if (this.connectionTime != null && this.latency != null && this.bandwidth != null) {
			this.server.setPerformancePreferences(this.connectionTime, this.latency, this.bandwidth);
		}
		// 绑定主机,端口号
		this.server.bind(new InetSocketAddress(this.hostName, this.port));

		// 创建线程池
		this.executor = Executors.newFixedThreadPool(this.threadPoolSize);

		// 打印日志
		this.logger.info("服务(" + this.hostName + ":" + this.port + ")已注册");

		// 返回Socket服务
		return this.server;
	}

	@Override
	public void activate() {

		// 启动TCP服务器线程
		new Thread(new TCPServerThread()).start();

		// 打印日志
		this.logger.info("服务(" + this.hostName + ":" + this.port + ")已启动");
	}

	/**
	 * TCP服务器线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class TCPServerThread implements Runnable {

		@Override
		public void run() {

			// 无限循环,阻塞式监听客户端accept动作
			while (true) {

				try {
					// 客户端Socket连接
					Socket socket = server.accept();
					// 设置超时时间
					if (clientTimeout != null) {
						socket.setSoTimeout(clientTimeout);
					}
					// 设置活跃检测
					if (clientKeepAlive != null) {
						socket.setKeepAlive(clientKeepAlive);
					}
					// 设置缓冲延迟
					if (clientTcpNoDelay != null) {
						socket.setTcpNoDelay(clientTcpNoDelay);
					}

					// 线程调度者中添加处理客户端连接的新线程
					executor.execute(new TCPClientThread(socket));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * TCP客户端处理线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class TCPClientThread implements Runnable {

		// 客户端连接Socket
		private Socket socket;

		/**
		 * TCP客户端处理线程
		 * 
		 * @param socket
		 */
		public TCPClientThread(Socket socket) {

			this.socket = socket;
		}

		@Override
		public void run() {
			tcpProtocol.handle(this.socket);
		}
	}
}
