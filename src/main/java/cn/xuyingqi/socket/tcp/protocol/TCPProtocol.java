package cn.xuyingqi.socket.tcp.protocol;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * TCP/IP协议
 * 
 * @author XuYQ
 *
 */
public interface TCPProtocol {

	/**
	 * 接收一个SocketChannel的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void handleAccept(SelectionKey key) throws IOException;

	/**
	 * 从一个SocketChannel读取信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void handleRead(SelectionKey key) throws IOException;

	/**
	 * 向一个SocketChannel写入信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void handleWrite(SelectionKey key) throws IOException;
}
