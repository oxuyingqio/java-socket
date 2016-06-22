package cn.xuyingqi.socket.util;

import org.junit.Test;

import cn.xuyingqi.util.util.ByteUtils;

public class MACTest {

	@Test
	public void test() {

		byte[] data = { 0, 0, 0, 0, 0, 0, 0, 3 };
		byte[] key = { 0, 0, 0, 0, 0, 0, 0, 1 };
		byte[] vector = { 0, 0, 0, 0, 0, 0, 0, 2 };

		byte[] mac = MAC.mac(data, key, vector);
		for (int i = 0; i < mac.length; i++) {

			System.out.println(Integer.toHexString(ByteUtils.byte2Int(mac[i])));
		}
	}
}
