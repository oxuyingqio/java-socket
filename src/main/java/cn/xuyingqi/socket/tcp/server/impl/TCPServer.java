package cn.xuyingqi.socket.tcp.server.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 阻塞式TCP服务器
 * 
 * @author XuYQ
 *
 */
public class TCPServer implements cn.xuyingqi.socket.tcp.server.TCPServer {

	// 默认的主机名称
	private static final String DEFAULT_HOSTNAME = "127.0.0.1";
	// 默认的端口号
	private static final int DEFAULT_PORT = 60000;

	// 日志
	private Logger logger = Logger.getLogger(cn.xuyingqi.socket.tcp.server.TCPServer.class);

	// 主机名称
	private String hostName = DEFAULT_HOSTNAME;
	// 端口号
	private int port = DEFAULT_PORT;

	@Override
	public String getHostName() {
		return null;
	}

	@Override
	public void setHostName(String hostName) {

	}

	@Override
	public int getPort() {
		return 0;
	}

	@Override
	public void setPort(int port) {

	}

	@Override
	public void init() throws IOException {

	}

	@Override
	public void activate() {

	}
}
