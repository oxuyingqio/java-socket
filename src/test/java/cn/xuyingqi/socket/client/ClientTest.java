package cn.xuyingqi.socket.client;

import org.junit.Test;

public class ClientTest {

	@Test
	public void test() {
		byte[] a = "2a".getBytes();

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
