package cn.xuyingqi.socket.server.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * ServerSocket工厂
 * 
 * @author XuYQ
 *
 */
public class ServerSocketFactory extends javax.net.ServerSocketFactory {

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		return null;
	}

	@Override
	public ServerSocket createServerSocket(int port, int backlog) throws IOException {
		return null;
	}

	@Override
	public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress) throws IOException {
		return null;
	}
}
