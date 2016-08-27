package cn.xuyingqi.socket.tcp.client;

import java.io.IOException;
import java.util.Arrays;

import cn.xuyingqi.socket.tcp.exception.ConnectRefusedException;

public class TCPClientTest {

	public static void main(String[] args) {
		new TCPClientTest().demo();
	}

	public void demo() {
//		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Demo());
			t.start();
//		}
	}

	private class Demo implements Runnable {

		@Override
		public void run() {
			TCPClient client = new cn.xuyingqi.socket.tcp.client.impl.TCPClient();
			client.setHostName("192.168.157.82");
			client.setPort(60000);
			client.init();
			try {
				client.connect();

				client.sendMsg(new byte[] { 0, 0, 0, 0, 0 });
				Thread.sleep(10000);
				
				byte[] a = client.receiveMsg();
				System.out.println(Arrays.toString(a));
				
				Thread.sleep(10000);
				
				client.sendMsg(new byte[] { 0, 0, 0, 0, 0 });
			
				client.close();
			} catch (IOException | ConnectRefusedException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
