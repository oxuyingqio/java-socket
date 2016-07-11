package cn.xuyingqi.socket.old;

import java.net.Socket;

import cn.xuyingqi.socket.servlet.ServletContext;

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
	public Client(ServletContext servletContext, Socket client) {

		this.client = client;
	}

	@Override
	public void run() {

		System.out.println(this.client);
	}
}
