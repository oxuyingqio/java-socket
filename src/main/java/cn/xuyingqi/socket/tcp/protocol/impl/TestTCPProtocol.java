package cn.xuyingqi.socket.tcp.protocol.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

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

	@Override
	public void handleRead(SelectionKey key) throws IOException {

		// 获取与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
		// 得到并清空缓冲区
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		buffer.clear();
		// 读取信息获得读取的字节数
		int readLength = clientChannel.read(buffer);
		if (readLength == -1) {
			// 没有读取到内容的情况
			clientChannel.close();
		} else {
			// 将缓冲区准备为数据传出状态
			buffer.flip();

			byte[] data = buffer.array();
			for (int i = 0; i < readLength; i++) {
				System.out.println(data[i]);
			}

			// 准备发送的文本
			String sendString = "卧室服务器";
			buffer = ByteBuffer.wrap(sendString.getBytes("GBK"));
			clientChannel.write(buffer);
			// 设置为下一次读取或是写入做准备
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {
		System.out.println("1111111111");
	}
}
