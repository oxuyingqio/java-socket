package cn.xuyingqi.socket.tcp.protocol.impl.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.NioTCPProtocol;

/**
 * 非阻塞式TCP协议抽象类
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractNioTCPProtocol implements NioTCPProtocol {

	// 日志
	private Logger logger = Logger.getLogger(NioTCPProtocol.class);

	// 缓冲区大小
	private int bufferSize;

	/**
	 * 非阻塞式TCP协议抽象类
	 * 
	 * @param bufferSize
	 */
	public AbstractNioTCPProtocol(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {

		// 获取与客户端连接的通道
		SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
		// 设置非阻塞
		clientChannel.configureBlocking(false);
		// 注册选择器,并设定可以进行读/写操作,以及设定缓冲区
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(this.bufferSize));

		// 打印日志
		this.logger.info(
				"客户端(" + clientChannel.socket().getInetAddress() + ":" + clientChannel.socket().getPort() + ")已连接");
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {

		// 获取与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
		// 得到并清空缓冲区
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		buffer.clear();
		// 读取信息获得读取的字节数
		int readLength = clientChannel.read(buffer);
		// 是否读取到数据
		if (readLength == -1) {
			// 打印日志
			this.logger.info("客户端(" + clientChannel.socket().getInetAddress() + ":" + clientChannel.socket().getPort()
					+ ")断开连接");
			// 关闭通道
			clientChannel.close();
		} else {
			// 字节消息
			StringBuffer byteMsg = new StringBuffer();
			for (int i = 0; i < readLength; i++) {
				byteMsg.append(buffer.array()[i]);
				byteMsg.append(" ");
			}

			// 打印日志
			this.logger.info("客户端(" + clientChannel.socket().getInetAddress() + ":" + clientChannel.socket().getPort()
					+ ")发送数据:" + byteMsg.toString());
		}
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {

	}
}
