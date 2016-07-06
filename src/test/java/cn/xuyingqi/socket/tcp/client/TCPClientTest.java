package cn.xuyingqi.socket.tcp.client;

import java.io.IOException;

import org.junit.Test;

import cn.xuyingqi.socket.tcp.client.impl.TCPClient;
import cn.xuyingqi.socket.tcp.exception.ConnectRefusedException;

public class TCPClientTest {

	@Test
	public void test() {

		TCPClient client = new TCPClient();
		client.init();
		try {
			client.connect();
			client.sendMsg("哈哈哈哈".getBytes("GBK"));
			System.out.println(new String(client.receiveMsg(), "GBK"));
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConnectRefusedException e) {
			e.printStackTrace();
		}
	}
}
