package cn.xuyingqi.socket.util;

import org.junit.Test;

import cn.xuyingqi.util.util.ByteUtils;

public class MACTest {

	@Test
	public void test() {

		byte[] data = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
				0x17, 0x18, 0x19, 0x20, 0x21, 0x22 };
		byte[] key = { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] vector = { 0, 0, 0, 0, 0, 0, 0, 0 };

		byte[] mac = MAC.mac(data, key, vector);
		for (int i = 0; i < mac.length; i++) {
			System.out.println(Integer.toHexString(ByteUtils.byte2Int(mac[i])));
		}
	}
}
