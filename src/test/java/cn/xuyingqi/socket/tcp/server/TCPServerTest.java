package cn.xuyingqi.socket.tcp.server;

import java.io.IOException;

import cn.xuyingqi.socket.tcp.protocol.impl.TestTCPProtocol;

/**
 * 阻塞式TCP服务测试
 * 
 * @author XuYQ
 *
 */
public class TCPServerTest {

	public static void main(String[] args) {

		// TCP服务器
		TCPServer tcpServer = new cn.xuyingqi.socket.tcp.server.impl.TCPServer(new TestTCPProtocol());
		// 注册,激活服务
		try {
			tcpServer.init();
			tcpServer.activate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
