package cn.xuyingqi.socket.tcp.protocol;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * 非阻塞式 TCP协议
 * 
 * @author XuYQ
 *
 */
public interface NioTCPProtocol {

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
