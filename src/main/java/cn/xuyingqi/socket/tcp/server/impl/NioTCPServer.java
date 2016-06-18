package cn.xuyingqi.socket.tcp.server.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import cn.xuyingqi.socket.tcp.protocol.NioTCPProtocol;
import cn.xuyingqi.socket.tcp.server.TCPServer;

/**
 * 非阻塞式 TCP服务器
 * 
 * @author XuYQ
 *
 */
public class NioTCPServer implements TCPServer {

	// 默认的主机名称
	private static final String DEFAULT_HOSTNAME = "127.0.0.1";
	// 默认的端口号
	private static final int DEFAULT_PORT = 60000;
	// 默认的超时时间
	private static final int DEFAULT_TIMEOUT = 3000;

	// 主机名称
	private String hostName = DEFAULT_HOSTNAME;
	// 端口号
	private int port = DEFAULT_PORT;
	// 超时时间
	private int timeOut = DEFAULT_TIMEOUT;

	// 非阻塞式TCP协议
	private NioTCPProtocol tcpProtocol;

	// 选择器
	private Selector selector;

	/**
	 * 非阻塞式 TCP服务器
	 * 
	 * @param tcpProtocol
	 *            非阻塞式TCP协议
	 */
	public NioTCPServer(NioTCPProtocol tcpProtocol) {
		this.tcpProtocol = tcpProtocol;
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
	 * 获取超时时间
	 * 
	 * @return
	 */
	public int getTimeOut() {
		return timeOut;
	}

	/**
	 * 设置超时时间
	 * 
	 * @param timeOut
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	@Override
	public void init() throws IOException {

		// 打开选择器
		this.selector = Selector.open();

		// 打开服务器通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 绑定主机,端口号
		serverChannel.socket().bind(new InetSocketAddress(this.hostName, this.port));
		// 设置非阻塞
		serverChannel.configureBlocking(false);
		// 注册选择器,并设定可进行Accept操作
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
	}

	@Override
	public void activate() {
		// 启动TCP服务器线程
		new Thread(new NioTCPServerThread()).start();
	}

	/**
	 * TCP服务器线程
	 * 
	 * @author XuYQ
	 *
	 */
	private class NioTCPServerThread implements Runnable {

		@Override
		public void run() {

			// 循环等待通道就绪
			while (true) {

				try {
					// 是否存在通道就绪
					selector.select();
					// 获取选择器的已选择键集
					Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
					// 遍历已选择键集
					while (keyIter.hasNext()) {

						// 选择键
						SelectionKey key = keyIter.next();
						try {
							// 是否准备好接收新的套接字连接
							if (key.isAcceptable()) {
								// 处理新接收的套接字
								tcpProtocol.handleAccept(key);
							}

							// 是否准备好进行读取,即是否存在客户端发来数据
							if (key.isReadable()) {
								// 读取客户端数据
								tcpProtocol.handleRead(key);
							}

							// 判断是否有效及可以发送给客户端
							if (key.isValid() && key.isWritable()) {
								// 向客户端写入数据
								tcpProtocol.handleWrite(key);
							}
						} catch (IOException ex) {
							// 移除处理过的键
							keyIter.remove();
							continue;
						}

						// 移除处理过的键
						keyIter.remove();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
