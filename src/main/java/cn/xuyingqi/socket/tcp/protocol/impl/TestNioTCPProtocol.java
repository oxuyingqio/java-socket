package cn.xuyingqi.socket.tcp.protocol.impl;

import cn.xuyingqi.socket.tcp.protocol.NioTCPProtocol;

/**
 * 非阻塞式TCP测试协议
 * 
 * @author XuYQ
 *
 */
public class TestNioTCPProtocol extends AbstractNioTCPProtocol implements NioTCPProtocol {

	/**
	 * 非阻塞式TCP测试协议
	 * 
	 * @param bufferSize
	 */
	public TestNioTCPProtocol(int bufferSize) {
		super(bufferSize);
	}
}
