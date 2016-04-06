package cn.xuyingqi.socket.server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 套接字消息处理器
 * 
 * @author Administrator
 *
 */
public interface SocketMsgHandler {

	/**
	 * 处理消息
	 * 
	 * @param is
	 * @param os
	 */
	public void handleMsg(String clientName, InputStream is, OutputStream os);
}
