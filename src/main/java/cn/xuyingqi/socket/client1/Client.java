package cn.xuyingqi.socket.client1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * 客户端套接字
 * 
 * @author Administrator
 *
 */
public class Client {
	// 日志
	private Logger logger = Logger.getLogger(Client.class);

	// 默认重新创建套接字连接时间
	private static final long DEFAULT_RECONNECT_TIME = 3000;
	// 默认重新创建套接字连接次数
	private static final int DEFAULT_RECONNECT_TIMES = 5;
	// 默认字符编码
	private static final String DEFAULT_CHARSET = "GBK";
	// 默认缓冲字节数
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	// IP地址
	private String host;
	// 端口号
	private int port;
	// 重新创建套接字连接时间
	private long reconnectTime = DEFAULT_RECONNECT_TIME;
	// 重新创建套接字连接次数
	private int reconnectTimes = DEFAULT_RECONNECT_TIMES;

	// 字符编码
	private String charset = DEFAULT_CHARSET;

	// 套接字
	private Socket socket;
	// 字节输入流
	private InputStream is;
	// 字节输出流
	private OutputStream os;

	/**
	 * 客户端套接字
	 * 
	 * @param host
	 *            IP地址
	 * @param port
	 *            端口号
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Client(String host, int port) throws UnknownHostException, IOException, InterruptedException {
		this.host = host;
		this.port = port;

		this.init();
	}

	/**
	 * 客户端套接字
	 * 
	 * @param host
	 *            IP地址
	 * @param port
	 *            端口号
	 * @param reconnectTime
	 *            重新创建套接字连接时间
	 * @param reconnectTimes
	 *            重新创建套接字连接次数
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Client(String host, int port, long reconnectTime, int reconnectTimes)
			throws UnknownHostException, IOException, InterruptedException {
		this.host = host;
		this.port = port;
		this.reconnectTime = reconnectTime;
		this.reconnectTimes = reconnectTimes;

		this.init();
	}

	/**
	 * 初始化客户端套接字
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void init() throws UnknownHostException, IOException, InterruptedException {
		// 创建套接字连接次数
		int connectTimes = 1;
		// 开启创建套接字连接
		while (this.socket == null || this.socket.isClosed()) {
			try {
				// 创建套接字连接
				this.socket = new Socket(this.host, this.port);
				// 获取字节输入流
				this.is = this.socket.getInputStream();
				// 获取字节输出流
				this.os = this.socket.getOutputStream();
				// 日志
				this.logger.info("与服务器(" + this.host + ":" + this.port + ")已连接");
			} catch (ConnectException | NoRouteToHostException e) {
				// 判断是否超出最大连接次数
				if (connectTimes <= this.reconnectTimes) {
					// 日志
					this.logger.warn("与服务器(" + this.host + ":" + this.port + ")第" + connectTimes + "次连接失败，将于"
							+ (this.reconnectTime / 1000) + "秒后重新连接");
					// 睡眠等待
					Thread.sleep(this.reconnectTime);
					// 创建套接字连接次数增加
					connectTimes++;
				} else {
					// 日志
					this.logger.error("与服务器(" + this.host + ":" + this.port + ")连接失败");
					// 抛出异常
					throw new ConnectException();
				}
			}
		}
	}

	/**
	 * 设置消息字符编码
	 * 
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 输出消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	private void output(byte[] msg) throws IOException {
		// 使用缓冲字节输出流
		BufferedOutputStream bos = new BufferedOutputStream(this.os);
		// 发送消息
		bos.write(msg);
		try {
			bos.flush();
		} catch (SocketException e) {
			try {
				this.reCon();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		// 日志
		this.logger.info("向服务器(" + this.host + ":" + this.port + ")发送消息：" + new String(msg, this.charset));
	}

	/**
	 * 输入消息
	 * 
	 * @return
	 * @throws IOException
	 */
	private byte[] input() throws IOException {
		// 使用缓冲字节输入流
		BufferedInputStream bis = new BufferedInputStream(this.is);
		// 读取消息
		byte[] msg = new byte[DEFAULT_BUFFER_SIZE];
		int temp = 0;
		while ((temp = bis.read(msg)) != -1) {
			// 日志
			this.logger.info("接收服务器(" + this.host + ":" + this.port + ")消息：" + new String(msg, 0, temp, this.charset));
			return msg;
		}

		return msg;
	}

	/**
	 * 向服务器发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void sendMsg(byte[] msg) throws IOException {
		this.output(msg);
	}

	/**
	 * 向服务器发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void sendMsg(int msg) throws IOException {
		this.output(String.valueOf(msg).getBytes(this.charset));
	}

	/**
	 * 向服务器发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void sendMsg(char[] msg) throws IOException {
		this.output(String.valueOf(msg).getBytes(this.charset));
	}

	/**
	 * 向服务器发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void sendMsg(String msg) throws IOException {
		this.output(msg.getBytes(this.charset));
	}

	/**
	 * 接收服务器消息转成字节数组
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] acceptMsg2ByteArray() throws IOException {
		return this.input();
	}

	/**
	 * 接收服务器消息转成字符串
	 * 
	 * @return
	 * @throws IOException
	 */
	public String acceptMsg2String() throws IOException {
		return new String(this.input(), this.charset);
	}

	/**
	 * 关闭客户端套接字连接
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		// 若套接字不为空
		if (this.socket != null && this.socket.isConnected()) {
			// 关闭字节输入流
			this.is.close();
			// 关闭字节输出流
			this.os.close();
			// 关闭套接字连接
			this.socket.close();
			// 日志
			this.logger.info("与服务器(" + this.host + ":" + this.port + ")已断开");
		}
	}

	public void reCon() throws IOException, InterruptedException {
		this.close();
		this.init();
	}
}
