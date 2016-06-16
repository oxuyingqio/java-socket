package cn.xuyingqi.socket.server;

import java.io.IOException;

import cn.xuyingqi.socket.server.Server;

public class ServerSimulator {
	public static void main(String[] args) {
		try {
			Server server = new Server(60000);
			server.activate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
