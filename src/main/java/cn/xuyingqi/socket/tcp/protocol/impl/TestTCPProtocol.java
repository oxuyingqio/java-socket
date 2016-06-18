package cn.xuyingqi.socket.tcp.protocol.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP测试协议
 * 
 * @author XuYQ
 *
 */
public class TestTCPProtocol implements TCPProtocol {

	// 日志
	private Logger logger = Logger.getLogger(TCPProtocol.class);

	@Override
	public void handle(Socket socket) {

		// 获取客户端信息
		InetAddress inetAddress = socket.getInetAddress();
		int port = socket.getPort();
		// 日志
		this.logger.info("客户端(" + inetAddress + ":" + port + ")已连接");

		try {
			// // 关闭字节输入流
			// this.is.close();
			// // 关闭字节输出流
			// this.os.close();
			// 关闭套接字
			socket.close();
			// 日志
			this.logger.info("客户端(" + inetAddress + ":" + port + ")已断开");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
