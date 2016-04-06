package cn.xuyingqi.socket.simulator;

import java.io.IOException;
import java.net.UnknownHostException;

import cn.xuyingqi.socket.client.Client;

public class ClientSimulator implements Runnable {

	@Override
	public void run() {
		try {
			Client client = new Client("127.0.0.1", 11021);
			for (int i = 10; i > 0; i--) {
				client.sendMsg("距离断开连接，剩余" + i + "秒");
				client.acceptMsg2String();
				Thread.sleep(1000);
			}
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			ClientSimulator cs = new ClientSimulator();
			Thread t = new Thread(cs);
			t.start();
		}
	}
}
