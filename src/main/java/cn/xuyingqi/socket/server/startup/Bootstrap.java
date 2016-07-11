package cn.xuyingqi.socket.server.startup;

import cn.xuyingqi.socket.server.connector.Connector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public class Bootstrap {

	public static void main(String[] args) {

		Connector server = new Connector();
		server.init();
		server.activate();
	}
}
