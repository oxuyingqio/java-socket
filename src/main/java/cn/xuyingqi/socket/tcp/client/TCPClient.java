package cn.xuyingqi.socket.tcp.client;

import java.io.IOException;
import java.net.Socket;

import cn.xuyingqi.socket.tcp.exception.ConnectRefusedException;

/**
 * TCP客户端
 * 
 * @author XuYQ
 *
 */
public interface TCPClient {

	/**
	 * 获取连接主机名称
	 * 
	 * @return
	 */
	public String getHostName();

	/**
	 * 设置连接主机名称
	 * 
	 * @param hostName
	 */
	public void setHostName(String hostName);

	/**
	 * 获取连接端口号
	 * 
	 * @return
	 */
	public int getPort();

	/**
	 * 设置连接端口号
	 * 
	 * @param port
	 */
	public void setPort(int port);

	/**
	 * 客户端初始化
	 * 
	 * @return
	 */
	public Socket init();

	/**
	 * 连接服务器
	 */
	public void connect() throws IOException, ConnectRefusedException;

	/**
	 * 发送消息
	 * 
	 * @param msg
	 */
	public void sendMsg(byte[] msg) throws IOException;

	/**
	 * 接收消息
	 * 
	 * @return
	 */
	public byte[] receiveMsg() throws IOException;

	/**
	 * 关闭客户端
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException;
}
