package cn.xuyingqi.socket.bio.protocol;

import java.net.Socket;

import cn.xuyingqi.net.protocol.Datagram;

/**
 * 解码器
 * 
 * @author XuYQ
 *
 */
public interface Decoder {

	/**
	 * 解码器
	 * 
	 * @param socket
	 * @param byteArray
	 * @return
	 */
	public Datagram decode(Socket socket, byte[] byteArray);
}
