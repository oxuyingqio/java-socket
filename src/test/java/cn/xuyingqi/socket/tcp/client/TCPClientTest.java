package cn.xuyingqi.socket.tcp.client;

import java.io.IOException;

import cn.xuyingqi.socket.tcp.exception.ConnectRefusedException;

public class TCPClientTest {

	public static void main(String[] args) {
		new TCPClientTest().demo();
	}

	public void demo() {
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Demo());
			t.start();
		}
	}

	private class Demo implements Runnable {

		@Override
		public void run() {
			TCPClient client = new cn.xuyingqi.socket.tcp.client.impl.TCPClient();
			client.setHostName("192.168.70.124");
			client.setPort(9096);
			client.init();
			try {
				client.connect();

				while (true) {
					client.sendMsg(new byte[] { 1, 1, 1, 0, 0, 0, 0, 0, 0, -39, 3, 0, 0, 0, 52, 32, 22, 8, 9, 4, 18, 84,
							0, 0, 1, 5, 0, 1, 0, 1, 1, 0, -94, 19, 16, -111, -1, -1, -127, 21, 105, 119, 120, 102, 124,
							-122, -72, -92, 12, 40, 61, -102, 36, -46, 118, 0, 0, 4, 0, -1, -1, -1, -1, -1, -1, 35, -66,
							-124, -31, 108, -42, -4, 6, -66, -126, -1, 6, -88, -116, -25, 32, 64, 0, 16, 0, -80, 64,
							-80, 36, 94, 13, 28, 6, -73, 71, -65, 118, 18, 77, -56, 67, -69, -117, -90, 6, 2, 13, 24, 3,
							11, 27, 6, 0, 0, 0, 1, -16, -45, -61, -69, -89, 0, 0, 0, 0, 0, 0, -23, 0, 0, 0, 0, 9, 39,
							16, 6, 11, 73, 50, -10, -98, 125, 73, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 99, -33, -34, -114, -40, -89, 28, 117 });
				}
			} catch (IOException | ConnectRefusedException e) {
				e.printStackTrace();
			}
		}
	}
}
