package cn.xuyingqi.socket.client;

import java.io.IOException;
import java.net.UnknownHostException;

import cn.xuyingqi.socket.client1.Client;

public class ClientSimulator implements Runnable {

	@Override
	public void run() {
		Client client = null;
		try {
			client = new Client("192.168.70.122", 60000, 1000, Integer.MAX_VALUE);
			while (true) {
				client.sendMsg("1111111111111111");
				Thread.sleep(1000);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientSimulator cs = new ClientSimulator();
		Thread t = new Thread(cs);
		t.start();
	}
}
