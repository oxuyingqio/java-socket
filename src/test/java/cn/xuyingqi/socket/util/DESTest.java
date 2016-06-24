package cn.xuyingqi.socket.util;

import org.junit.Test;

public class DESTest {

	@Test
	public void test() {

		byte[] data = new byte[] { 0, 1, 1, 0, 0, 0, 0, 7, 91, -51, 21, 0, 0, 0, 25, 32, 17, 9, 7, 5, 35, 33, 0, 0, 0,
				6, 0, 0, 0, 7, 91, -51, 21, 0, 0, 0, 0, 0, 0, 0 };
		byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] sec = DES.encrypt(data, key);

		for (int i = 0; i < sec.length; i++) {
			System.out.println(sec[i]);
		}
	}
}
