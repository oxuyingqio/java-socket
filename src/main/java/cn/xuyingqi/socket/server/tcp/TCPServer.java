package cn.xuyingqi.socket.server.tcp;

import java.io.IOException;

/**
 * TCP/IP 服务器
 * 
 * @author XuYQ
 *
 */
public interface TCPServer {

	public void init() throws IOException;

	public void activate();
}
