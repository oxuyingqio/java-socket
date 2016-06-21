package cn.xuyingqi.socket.tcp.protocol.impl;

import java.net.Socket;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP协议
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractTCPProtocol implements TCPProtocol {

	@Override
	public abstract void handle(Socket socket);

}
