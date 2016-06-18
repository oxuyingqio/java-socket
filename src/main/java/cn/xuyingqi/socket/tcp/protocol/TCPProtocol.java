package cn.xuyingqi.socket.tcp.protocol;

import java.net.Socket;

/**
 * 阻塞式TCP协议
 * 
 * @author XuYQ
 *
 */
public interface TCPProtocol {

	/**
	 * 处理Socket
	 * 
	 * @param socket
	 */
	public void handle(Socket socket);
}
