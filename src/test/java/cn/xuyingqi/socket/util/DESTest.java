package cn.xuyingqi.socket.util;

import org.junit.Test;

import cn.xuyingqi.util.util.ByteUtils;

public class DESTest {

	@Test
	public void test() {

		byte[] data = new byte[] { 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16 };
		byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] sec = DES.encrypt(data, key);

		for (int i = 0; i < sec.length; i++) {
			System.out.println(Integer.toHexString(ByteUtils.byte2Int(sec[i])));
		}
	}
}
