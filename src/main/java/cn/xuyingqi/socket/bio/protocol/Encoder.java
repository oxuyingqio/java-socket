package cn.xuyingqi.socket.bio.protocol;

import java.net.Socket;

import cn.xuyingqi.net.protocol.Datagram;

/**
 * 编码器
 * 
 * @author XuYQ
 *
 */
public interface Encoder {

	/**
	 * 编码
	 * 
	 * @param socket
	 * @param datagram
	 */
	public void encode(Socket socket, Datagram datagram);
}
