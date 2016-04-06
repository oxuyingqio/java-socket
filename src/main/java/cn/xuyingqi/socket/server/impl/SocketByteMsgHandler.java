package cn.xuyingqi.socket.server.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.server.SocketMsgHandler;

/**
 * Byte消息处理器
 * 
 * @author Administrator
 *
 */
public class SocketByteMsgHandler implements SocketMsgHandler {
	// 日志
	private Logger logger = Logger.getLogger(SocketMsgHandler.class);

	// 默认字符编码
	private static final String DEFAULT_CHARSET = "GBK";
	// 默认缓冲大小
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	@Override
	public void handleMsg(String clientName, InputStream is, OutputStream os) {
		try {
			// 使用缓冲字节输入流
			BufferedInputStream bis = new BufferedInputStream(is);
			// 使用缓冲字节输出流
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 获取客户端消息
			byte[] msg = new byte[DEFAULT_BUFFER_SIZE];
			int temp = 0;
			while ((temp = bis.read(msg)) != -1) {
				// 日志
				this.logger.info("接收客户端(" + clientName + ")消息：" + new String(msg, 0, temp, DEFAULT_CHARSET));

				// 向客户端发送消息
				String returnMsg = "我是服务器";
				bos.write(returnMsg.getBytes(DEFAULT_CHARSET));
				bos.flush();
				// 日志
				this.logger.info("向客户端(" + clientName + ")发送消息：" + returnMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
