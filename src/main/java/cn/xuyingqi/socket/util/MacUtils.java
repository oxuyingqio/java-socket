package cn.xuyingqi.socket.util;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.util.exception.ByteArrayLengthErrorException;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 报文验证码(MAC)
 * 
 * @author XuYQ
 *
 */
public class MacUtils {

	/**
	 * 获取MAC值,1块=8字节
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param vector
	 *            初始向量
	 * @return
	 */
	public static byte[] getMac(byte[] data, byte[] key, byte[] vector) {

		// 若密钥或初始向量长度不为8,则抛字节数组长度错误异常
		if (key.length != 8 || vector.length != 8) {
			throw new ByteArrayLengthErrorException();
		}

		// 获取传入数据最后一块缺少的字节个数
		int miss = data.length % 8 == 0 ? 0 : (8 - (data.length % 8));
		// 声明需要计算MAC值的字节数组
		byte[] macData = new byte[data.length + miss];
		// 将传入数据复制至需要计算MAC值的字节数组中
		System.arraycopy(data, 0, macData, 0, data.length);
		// 将最后一块缺少的字节,补0
		for (int i = 0; i < miss; i++) {

			macData[data.length + i] = 0;
		}

		// 获取块数
		int blockLength = macData.length / 8;
		// 遍历每一块
		for (int i = 0; i < blockLength; i++) {

			// 获取异或字节数组
			byte[] xor = ByteUtils.byteArrayXOR(vector, ArrayUtils.subarray(macData, i * 8, (i + 1) * 8));
			desc(key,xor,vector);
		}

		return null;
	}

	public static void main(String[] args) {
		int[] i1 = { 1, 2, 3, 4, 5 };
		int[] i2 = new int[10];
		System.arraycopy(i1, 0, i2, 0, i1.length);

		for (int i = 0; i < i2.length; i++) {
			System.out.println(i2[i]);
		}
	}
}
