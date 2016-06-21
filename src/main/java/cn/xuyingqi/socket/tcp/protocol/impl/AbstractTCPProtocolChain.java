package cn.xuyingqi.socket.tcp.protocol.impl;

import java.net.Socket;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP协议-TCP协议抽象链
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractTCPProtocolChain implements TCPProtocol {

	// 后续的TCP协议
	private AbstractTCPProtocolChain tcpProtocol;

	@Override
	public abstract void handle(Socket socket);

	/**
	 * 获取后续的TCP协议
	 * 
	 * @return
	 */
	public AbstractTCPProtocolChain getTcpProtocol() {
		return tcpProtocol;
	}

	/**
	 * 设置后续的TCP协议
	 * 
	 * @param tcpProtocol
	 */
	public void setTcpProtocol(AbstractTCPProtocolChain tcpProtocol) {
		this.tcpProtocol = tcpProtocol;
	}
}
