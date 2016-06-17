package cn.xuyingqi.socket.tcp.protocol.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * TCP/IP协议抽象类
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractTCPProtocol implements TCPProtocol {

	// 日志
	private Logger logger = Logger.getLogger(TCPProtocol.class);

	// 缓冲区大小
	private int bufferSize;

	/**
	 * TCP/IP抽象类
	 * 
	 * @param bufferSize
	 */
	public AbstractTCPProtocol(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {

		// 获取与客户端连接的通道
		SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
		// 设置非阻塞
		clientChannel.configureBlocking(false);
		// 注册选择器,并设定可以进行读操作,以及设定缓冲区
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(this.bufferSize));

		// 打印日志
		this.logger.info(
				"客户端(" + clientChannel.socket().getInetAddress() + ":" + clientChannel.socket().getPort() + ")已连接");
	}
}
