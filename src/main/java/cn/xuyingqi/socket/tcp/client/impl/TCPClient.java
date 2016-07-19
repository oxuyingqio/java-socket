package cn.xuyingqi.socket.tcp.client.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.exception.ConnectRefusedException;

/**
 * 阻塞式TCP客户端
 * 
 * @author XuYQ
 *
 */
public class TCPClient implements cn.xuyingqi.socket.tcp.client.TCPClient {

	// 默认的主机名称
	private static final String DEFAULT_HOST_NAME = "127.0.0.1";
	// 默认的端口号
	private static final int DEFAULT_PORT = 60000;

	// 日志
	private Logger logger = Logger.getLogger(cn.xuyingqi.socket.tcp.client.TCPClient.class);

	// 主机名称
	private String hostName = DEFAULT_HOST_NAME;
	// 端口号
	private int port = DEFAULT_PORT;

	// 服务器Socket
	private Socket client;

	// 超时时间
	private Integer timeout;
	// 活跃检测
	private Boolean keepAlive;
	// 消除缓冲延迟
	private Boolean tcpNoDelay;

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
	 * 设置超时时间
	 * 
	 * @param timeout
	 */
	public void setTimeout(int timeout) {

		this.timeout = timeout;
	}

	/**
	 * 设置活跃度检测
	 * 
	 * @param keepAlive
	 */
	public void setKeepAlive(boolean keepAlive) {

		this.keepAlive = keepAlive;
	}

	/**
	 * 设置消除缓冲延迟
	 * 
	 * @param tcpNoDelay
	 */
	public void setTcpNoDelay(boolean tcpNoDelay) {

		this.tcpNoDelay = tcpNoDelay;
	}

	@Override
	public Socket init() {

		// 创建Socket客户端
		this.client = new Socket();

		// 打印日志
		this.logger.info("客户端已就绪");

		// 返回Socket客户端
		return this.client;
	}

	@Override
	public void connect() throws IOException, ConnectRefusedException {

		// 未连接,继续连接
		while (!(this.client.isConnected() && !this.client.isClosed())) {

			try {

				// 连接
				this.client.connect(new InetSocketAddress(this.hostName, this.port));
				// 设置超时时间
				if (this.timeout != null) {
					this.client.setSoTimeout(this.timeout);
				}
				// 设置活跃检测
				if (this.keepAlive != null) {
					this.client.setKeepAlive(this.keepAlive);
				}
				// 设置缓冲延迟
				if (this.tcpNoDelay != null) {
					this.client.setTcpNoDelay(this.tcpNoDelay);
				}

				// 打印日志
				this.logger.info("已连接服务器(" + this.hostName + ":" + this.port + ")");

			} catch (ConnectException e) {

				// 抛出失败异常
				throw new ConnectRefusedException();
			}
		}
	}

	@Override
	public void sendMsg(byte[] msg) throws IOException {

		BufferedOutputStream bos = new BufferedOutputStream(this.client.getOutputStream());
		bos.write(msg);
		bos.flush();
	}

	@Override
	public byte[] receiveMsg() throws IOException {

		// 使用缓冲字节输入流,输出流
		BufferedInputStream bis = new BufferedInputStream(this.client.getInputStream());

		// 获取终端输入的字节数组
		byte[] msg = new byte[1024];
		// 读取的字节数组长度
		@SuppressWarnings("unused")
		int readLength = 0;
		// 阻塞式读取终端数据
		while ((readLength = bis.read(msg)) != -1) {

			return msg;
		}

		return msg;
	}

	@Override
	public void close() throws IOException {

		this.client.close();
	}

	public static void main(String[] args) {
		TCPClient client = new TCPClient();
		try {
			client.init();
			client.connect();
		} catch (IOException | ConnectRefusedException e) {
			e.printStackTrace();
		}
	}
}
