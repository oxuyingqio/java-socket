package cn.xuyingqi.socket.tcp.protocol.impl;

import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * TCP协议链-记录.该协议仅做记录用
 * 
 * @author XuYQ
 *
 */
public class RecordTCPProtocolChain extends TCPProtocolChain implements TCPProtocol {

	// 日志
	private Logger logger = Logger.getLogger(TCPProtocol.class);

	@Override
	public void handle(Socket socket) {

		// 获取客户端信息
		InetAddress inetAddress = socket.getInetAddress();
		int port = socket.getPort();
		// 打印日志
		if (socket.isConnected() && !socket.isClosed()) {
			this.logger.info("客户端(" + inetAddress + ":" + port + ")已连接");
		}

		// 若存在后续的TCP协议,则调用后续的TCP协议
		if (this.getTcpProtocol() != null) {
			this.getTcpProtocol().handle(socket);
		}

		// 打印日志
		if (socket.isClosed()) {
			this.logger.info("客户端(" + inetAddress + ":" + port + ")已断开");
		}
	}
}
