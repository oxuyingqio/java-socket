package cn.xuyingqi.socket.tcp.protocol.impl;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * TCP/IP测试协议
 * 
 * @author XuYQ
 *
 */
public class TestTCPProtocol extends AbstractTCPProtocol implements TCPProtocol {

	/**
	 * TCP/IP测试协议
	 * 
	 * @param bufferSize
	 */
	public TestTCPProtocol(int bufferSize) {
		super(bufferSize);
	}
}
