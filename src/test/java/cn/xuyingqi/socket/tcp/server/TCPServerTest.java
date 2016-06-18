package cn.xuyingqi.socket.tcp.server;

import java.io.IOException;

import cn.xuyingqi.socket.tcp.protocol.impl.TestTCPProtocol;
import cn.xuyingqi.socket.tcp.server.impl.NioTCPServer;

/**
 * TCP/IP服务测试
 * 
 * @author XuYQ
 *
 */
public class TCPServerTest {

	public static void main(String[] args) {

		// TCP/IP服务
		TCPServer tcpServer = new NioTCPServer();
		// 设置TCP/IP协议
		((NioTCPServer) tcpServer).setTcpProtocol(new TestTCPProtocol(1024));
		// 激活服务
		try {
			tcpServer.init();
			tcpServer.activate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
