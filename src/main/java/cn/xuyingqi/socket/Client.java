package cn.xuyingqi.socket;

import java.net.Socket;

/**
 * 客户端
 * 
 * @author XuYQ
 *
 */
public class Client implements Runnable {

	/**
	 * 客户端
	 */
	private Socket client;

	/**
	 * 客户端
	 * 
	 * @param client
	 */
	public Client(Socket client) {

		this.client = client;
	}

	@Override
	public void run() {
		
		
	}
}
