package cn.xuyingqi.socket.tcp.server;

import java.io.IOException;

import cn.xuyingqi.socket.tcp.protocol.impl.TestTCPProtocol;

public class TCPServerTest {

	public static void main(String[] args) {

		// TCP/IP服务
		TCPServer tcpServer = new TCPServer();
		// 设置TCP/IP协议
		tcpServer.setTcpProtocol(new TestTCPProtocol(1024));
		// 激活服务
		try {
			tcpServer.activate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
