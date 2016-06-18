package cn.xuyingqi.socket.tcp.server;

import java.io.IOException;

/**
 * TCP服务器
 * 
 * @author XuYQ
 *
 */
public interface TCPServer {

	/**
	 * 获取主机名称
	 * 
	 * @return
	 */
	public String getHostName();

	/**
	 * 设置主机名称
	 * 
	 * @param hostName
	 */
	public void setHostName(String hostName);

	/**
	 * 获取端口号
	 * 
	 * @return
	 */
	public int getPort();

	/**
	 * 设置端口号
	 * 
	 * @param port
	 */
	public void setPort(int port);

	/**
	 * 服务器初始化
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException;

	/**
	 * 激活服务器
	 */
	public void activate();
}
