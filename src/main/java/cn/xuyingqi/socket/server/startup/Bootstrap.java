package cn.xuyingqi.socket.server.startup;

import cn.xuyingqi.socket.server.connector.SocketConnector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public class Bootstrap {

	public static void main(String[] args) {

		SocketConnector server = new SocketConnector();
		server.init();
		server.startup();
	}
}
