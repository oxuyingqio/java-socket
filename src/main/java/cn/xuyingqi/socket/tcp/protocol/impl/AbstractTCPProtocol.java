package cn.xuyingqi.socket.tcp.protocol.impl;

import java.net.Socket;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP协议-责任链抽象处理者
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractTCPProtocol implements TCPProtocol {

	// 后续的TCP协议处理者
	private AbstractTCPProtocol tcpProtocol;

	@Override
	public abstract void handle(Socket socket);

	/**
	 * 获取后续的TCP协议处理者
	 * 
	 * @return
	 */
	public AbstractTCPProtocol getTcpProtocol() {
		return tcpProtocol;
	}

	/**
	 * 设置后续的TCP协议处理者
	 * 
	 * @param tcpProtocol
	 */
	public void setTcpProtocol(AbstractTCPProtocol tcpProtocol) {
		this.tcpProtocol = tcpProtocol;
	}
}
