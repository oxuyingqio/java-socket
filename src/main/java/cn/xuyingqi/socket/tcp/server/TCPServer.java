package cn.xuyingqi.socket.tcp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * TCP/IP服务器
 * 
 * @author XuYQ
 *
 */
public class TCPServer {

	// 默认的主机名称
	private static final String DEFAULT_HOSTNAME = "127.0.0.1";
	// 默认的端口号
	private static final int DEFAULT_PORT = 60000;
	// 默认的超时时间
	private static final int DEFAULT_TIMEOUT = 3000;

	// 日志
	private Logger logger = Logger.getLogger(TCPServer.class);

	// 主机名称
	private String hostName = DEFAULT_HOSTNAME;
	// 端口号
	private int port = DEFAULT_PORT;
	// 超时时间
	private int timeOut = DEFAULT_TIMEOUT;

	// TCP/IP协议
	private TCPProtocol tcpProtocol;

	/**
	 * 获取主机名称
	 * 
	 * @return
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * 设置主机名称
	 * 
	 * @param hostName
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * 获取端口号
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 设置端口号
	 * 
	 * @param port
	 */
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

	/**
	 * 获取TCP/IP协议
	 * 
	 * @return
	 */
	public TCPProtocol getTcpProtocol() {
		return tcpProtocol;
	}

	/**
	 * 设置TCP/IP协议
	 * 
	 * @param tcpProtocol
	 */
	public void setTcpProtocol(TCPProtocol tcpProtocol) {
		this.tcpProtocol = tcpProtocol;
	}

	/**
	 * 激活服务
	 * 
	 * @throws IOException
	 */
	public void activate() throws IOException {

		// 打开选择器
		Selector selector = Selector.open();

		// 打开服务器通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 绑定主机,端口号
		serverChannel.socket().bind(new InetSocketAddress(this.hostName, this.port));
		// 设置非阻塞
		serverChannel.configureBlocking(false);
		// 注册选择器,并设定可进行Accept操作
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		// 循环等待通道就绪
		while (true) {

			// 是否存在通道就绪
			if (selector.select(this.timeOut) == 0) {
				// 打印日志
				this.logger.info("暂无待处理通道");
				continue;
			}

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
						this.tcpProtocol.handleAccept(key);
					}

					// 是否准备好进行读取,即是否存在客户端发来数据
					if (key.isReadable()) {
						// 读取客户端数据
						this.tcpProtocol.handleRead(key);
					}

					// 判断是否有效及可以发送给客户端
					if (key.isValid() && key.isWritable()) {
						// 向客户端写入数据
						this.tcpProtocol.handleWrite(key);
					}
				} catch (IOException ex) {
					// 移除处理过的键
					keyIter.remove();
					continue;
				}

				// 移除处理过的键
				keyIter.remove();
			}
		}
	}
}
