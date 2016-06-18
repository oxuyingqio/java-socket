package cn.xuyingqi.socket.tcp.protocol.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.xuyingqi.socket.tcp.protocol.TCPProtocol;

/**
 * 阻塞式TCP测试协议
 * 
 * @author XuYQ
 *
 */
public class TestTCPProtocol implements TCPProtocol {

	// 日志
	private Logger logger = Logger.getLogger(TCPProtocol.class);

	@Override
	public void handle(Socket socket) {

		// 获取客户端信息
		InetAddress inetAddress = socket.getInetAddress();
		int port = socket.getPort();
		// 打印日志
		this.logger.info("客户端(" + inetAddress + ":" + port + ")已连接");

		// 使用缓冲字节输入流
		try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());) {

			// 获取客户端信息
			byte[] msg = new byte[1024];
			// 读取的字节数
			int readLength = 0;
			// 阻塞式读取客户端信息
			while ((readLength = bis.read(msg)) != -1) {

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < readLength; i++) {
					sb.append(msg[i]);
					sb.append(" ");
				}

				// 打印日志
				this.logger.info("客户端发送消息：" + sb.toString());
			}

			// 关闭字节输入流
			bis.close();
			// 关闭套接字
			socket.close();
			// 打印日志
			this.logger.info("客户端(" + inetAddress + ":" + port + ")已断开");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
