package cn.xuyingqi.socket.bio.server.connector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.server.connector.ConnectorConfig;
import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.socket.bio.protocol.Protocol;
import cn.xuyingqi.socket.bio.server.container.ProtocolContainer;
import cn.xuyingqi.socket.bio.server.container.ServletContainer;
import cn.xuyingqi.socket.bio.servlet.facade.ServerServletRequestFacade;
import cn.xuyingqi.socket.bio.servlet.facade.ServerServletResponseFacade;
import cn.xuyingqi.socket.bio.servlet.facade.ServletSessionFacade;
import cn.xuyingqi.socket.bio.servlet.impl.DefaultServerServletRequest;
import cn.xuyingqi.socket.bio.servlet.impl.DefaultServerServletResponse;
import cn.xuyingqi.socket.bio.servlet.impl.DefaultServletSession;

/**
 * 连接器
 * 
 * @author XuYQ
 *
 */
public final class Connector implements cn.xuyingqi.net.server.connector.Connector {

	/**
	 * 连接器配置
	 */
	private ConnectorConfig config;

	/**
	 * Socket服务
	 */
	private ServerSocket server;
	/**
	 * 线程池调度者
	 */
	private ExecutorService executor;
	/**
	 * Servlet容器
	 */
	private ServletContainer servletContainer = ServletContainer.getInstance();

	@Override
	public final void init(ConnectorConfig config) {

		// 获取连接器配置
		this.config = config;
	}

	@Override
	public final void connect() {

		try {

			// 设置线程池
			this.executor = Executors.newFixedThreadPool(this.config.getMaxConnections());

			// 创建socket服务
			this.server = new ServerSocket();
			// 绑定IP,端口号
			this.server.bind(new InetSocketAddress(this.config.getHost(), this.config.getPort()));
			// 启动服务监听
			new Thread(new ServerListenerThread()).start();
			// 打印日志
			System.out.println(this.config.getHost() + ":" + this.config.getPort() + " 已启动");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Socket服务监听线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class ServerListenerThread implements Runnable {

		@Override
		public void run() {

			// 无限循环,阻塞式监听客户端accept动作
			while (true) {

				try {

					// 线程池增加处理客户端线程
					executor.execute(new ClientProcessThread(server.accept()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 客户端处理线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class ClientProcessThread implements Runnable {

		/**
		 * 客户端连接
		 */
		private Socket socket;
		/**
		 * 协议
		 */
		private Protocol protocol = ProtocolContainer.getInstance().getProtocol(config.getProtocol());

		/**
		 * 客户端处理线程
		 * 
		 * @param socket
		 */
		public ClientProcessThread(Socket socket) {

			// 设置客户端连接
			this.socket = socket;

			try {

				// 设置超时时间
				this.socket.setSoTimeout(config.getTimeout() * 1000);
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			// 创建Servlet会话对象
			DefaultServletSession servletSession = new DefaultServletSession(UUID.randomUUID().toString(),
					(InetSocketAddress) this.socket.getRemoteSocketAddress(),
					(InetSocketAddress) this.socket.getLocalSocketAddress());

			// 使用缓冲字节输入流,输出流
			try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());) {

				// 获取终端输入的字节数组
				byte[] msg = new byte[1024 * 1024];
				// 读取的字节数组长度
				int length = 0;
				// 阻塞式读取终端数据
				while ((length = bis.read(msg)) != -1) {

					// 获取解码后对象
					Datagram datagram = this.protocol.getDecoder().decode(this.socket,
							ArrayUtils.subarray(msg, 0, length));

					// 修改最后一次请求时间
					servletSession.updateLastAccessedTime();
					// Servlet会话外观类
					ServletSession servletSessionFacade = new ServletSessionFacade(servletSession);

					// Servlet请求
					DefaultServerServletRequest request = new DefaultServerServletRequest(servletSessionFacade,
							datagram);
					// Servlet请求外观类
					ServerServletRequest requestFacade = new ServerServletRequestFacade(request);

					// Servlet响应
					DefaultServerServletResponse response = new DefaultServerServletResponse(requestFacade);
					// Servlet响应外观类
					ServerServletResponse responseFacade = new ServerServletResponseFacade(response);

					// 获取Servlet名称集合
					Iterator<String> it = servletContainer.getServletNames().iterator();
					// 遍历Servlet名称集合
					while (it.hasNext()) {

						// 获取当前Servlet
						Servlet servlet = servletContainer.getServlet(it.next());
						// Servet会话中设置当前Servlet上下文
						servletSession.setServletContext(servlet.getServletConfig().getServletContext());
						// 调用Servlet服务方法
						servlet.service(requestFacade, responseFacade);
					}
					// 判断响应报文不为空
					if (response.getDatagram() != null) {
						// 写入响应数据报文
						this.protocol.getEncoder().encode(this.socket, datagram);
					}

				}

				// 关闭字节输入流
				bis.close();
				// 关闭套接字
				this.socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
