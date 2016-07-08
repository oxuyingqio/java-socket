package cn.xuyingqi.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.ServerConfig.ClientPreferences;
import cn.xuyingqi.socket.ServerConfig.ServerPreferences;
import cn.xuyingqi.socket.ServerConfig.ServerPreferences.PerformancePreferences;
import cn.xuyingqi.socket.servlet.Servlet;

/**
 * 服务器
 * 
 * @author XuYQ
 *
 */
public class Server {

	/**
	 * 日志
	 */
	private Logger logger = Logger.getLogger(Server.class);

	/**
	 * 服务器配置
	 */
	private ServerConfig config;

	/**
	 * 服务器Socket
	 */
	private ServerSocket server;
	/**
	 * 线程池调度者
	 */
	private ExecutorService executor;

	/**
	 * Servlet
	 */
	private Servlet servlet;

	/**
	 * 服务器
	 */
	public Server() {

		// 初始化服务器
		this.init();
		// 激活服务器
		this.activate();
	}

	/**
	 * 初始化服务器
	 */
	private void init() {

		// 获取服务器配置
		this.config = ServerConfig.newInstance();

		// 获取服务器参数
		ServerPreferences sp = this.config.getServerPreferences();

		try {

			// 创建Socket服务器
			this.server = new ServerSocket();
			// 设置Socket服务器性能
			PerformancePreferences pp = null;
			if ((pp = sp.getPerformancePreferences()) != null) {
				this.server.setPerformancePreferences(pp.getConnectionTime(), pp.getLatency(), pp.getBandwidth());
			}
			// 绑定主机,端口号
			this.server.bind(new InetSocketAddress(sp.getHostName(), sp.getPort()));

			// 创建线程池
			this.executor = Executors.newFixedThreadPool(sp.getMaxConnections());

			// 打印日志
			this.logger.info("服务器(" + sp.getHostName() + ":" + sp.getPort() + ")已注册");

		} catch (IOException e) {

			// 打印参数
			this.logger.error("服务器(" + sp.getHostName() + ":" + sp.getPort() + ")注册失败");
		}
	}

	/**
	 * 激活服务器
	 */
	private void activate() {

		// 启动TCP服务器线程
		new Thread(new ServerThread()).start();

		// 获取服务器参数
		ServerPreferences sp = this.config.getServerPreferences();
		// 打印日志
		this.logger.info("服务器(" + sp.getHostName() + ":" + sp.getPort() + ")已启动");
	}

	/**
	 * 服务器线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class ServerThread implements Runnable {

		@Override
		public void run() {

			// 无限循环,监听客户端连接
			while (true) {

				try {

					// 客户端
					Socket client = server.accept();
					// 获取客户端参数
					ClientPreferences cp = config.getClientPreferences();
					// 参数不为空
					if (cp != null) {

						// 设置超时时间
						if (cp.getTimeout() != null) {
							client.setSoTimeout(cp.getTimeout());
						}
						// 设置活跃检测
						if (cp.getKeepAlive() != null) {
							client.setKeepAlive(cp.getKeepAlive());
						}
						// 设置是否消除缓冲延迟
						if (cp.getTcpNoDelay() != null) {
							client.setTcpNoDelay(cp.getTcpNoDelay());
						}
					}

					// 线程调度者中添加客户端连接的新线程
					executor.execute(new Client(client));

				} catch (IOException e) {

					logger.error("客户端连接失败");
				}
			}
		}
	}

	public static void main(String[] args) {

		new Server();
	}
}
