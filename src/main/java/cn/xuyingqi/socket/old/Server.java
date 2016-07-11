package cn.xuyingqi.socket.old;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.old.ServerConfig.ClientPreferences;
import cn.xuyingqi.socket.old.ServerConfig.ServerPreferences;
import cn.xuyingqi.socket.old.ServerConfig.ServerPreferences.PerformancePreferences;
import cn.xuyingqi.socket.servlet.Servlet;
import cn.xuyingqi.socket.servlet.ServletConfig;

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
		// 打印日志
		this.logger.info("服务器初始化完成");

		// 激活服务器
		this.activate();
		// 打印日志
		this.logger.info("服务器激活完成");
	}

	/**
	 * 初始化服务器
	 */
	private void init() {

		// 初始化服务器配置
		this.initConfig();
		// 打印日志
		this.logger.info("服务器配置初始化完成");

		// 初始化Socket服务器
		this.initServer();
		// 打印日志
		this.logger.info("Socket服务初始化完成");

		// 初始化Servlet
		this.initServlet();
		// 打印日志
		this.logger.info("Servlet初始化完成");
	}

	/**
	 * 初始化服务器配置
	 */
	private void initConfig() {

		// 获取服务器配置
		this.config = ServerConfig.newInstance();
	}

	/**
	 * 初始化Socket服务器
	 */
	private void initServer() {

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
	 * 初始化Servlet
	 */
	@SuppressWarnings("unchecked")
	private void initServlet() {

		// Servlet名称
		String name = this.config.getServlet().getName();
		// Servlet类路径
		String clazz = this.config.getServlet().getClazz();

		try {

			// 实例化Servlet
			this.servlet = ((Class<Servlet>) Class.forName(clazz)).newInstance();
			// 打印日志
			this.logger.info("Servlet实例化完成");

			// 初始化ServletConfig
			this.initServletConfig();

		} catch (ClassNotFoundException e) {

			this.logger.error("获取Servlet(" + name + "):" + clazz + "未找到");

		} catch (InstantiationException | IllegalAccessException e) {

			this.logger.error("获取Servlet(" + name + "):" + clazz + "实例化失败");

		}
	}

	@SuppressWarnings("unchecked")
	private void initServletConfig() {

		// Servlet配置类路径
		String clazz = this.config.getServletConfig().getClazz();

		try {

			// 实例化Servlet配置
			ServletConfig servletConfig = ((Class<ServletConfig>) Class.forName(clazz)).newInstance();
			// 打印日志
			this.logger.info("ServletConfig实例化完成");

			// Servlet加载Servlet配置
			this.servlet.init(servletConfig);

		} catch (ClassNotFoundException e) {

			this.logger.error("获取ServletConfig:" + clazz + "未找到");

		} catch (InstantiationException | IllegalAccessException e) {

			this.logger.error("获取ServletConfig:" + clazz + "实例化失败");

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
					executor.execute(new Client(servlet.getServletConfig().getServletContext(), client));

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
