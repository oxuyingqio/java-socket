package cn.xuyingqi.socket.util;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.util.exception.ByteArrayLengthErrorException;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * MAC消息认证码,1块=8字节
 * 
 * @author XuYQ
 *
 */
public class MAC {

	/**
	 * 获取MAC值
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param vector
	 *            初始向量
	 * @return
	 */
	public static byte[] mac(byte[] data, byte[] key, byte[] vector) {

		// 若密钥或初始向量长度不为8,则抛字节数组长度错误异常
		if (key.length != 8 || vector.length != 8) {
			throw new ByteArrayLengthErrorException();
		}

		// 获取传入数据最后一块缺少的字节个数
		int miss = data.length % 8 == 0 ? 0 : (8 - (data.length % 8));

		// 声明需要计算MAC值的字节数组,长度为数据长度+缺少的字节个数
		byte[] macData = new byte[data.length + miss];
		// 将传入数据复制至需要计算MAC值的字节数组中
		System.arraycopy(data, 0, macData, 0, data.length);
		// 将最后一块缺少的字节,补0
		for (int i = 0; i < miss; i++) {

			macData[data.length + i] = 0;
		}

		// MAC值
		byte[] mac = new byte[0];

		// 获取对应块数
		int blockLength = macData.length / 8;
		// 遍历每一块
		for (int i = 0; i < blockLength; i++) {

			// 获取异或字节数组
			byte[] xor = ByteUtils.xor(vector, ArrayUtils.subarray(macData, i * 8, (i + 1) * 8));
			// 对异或字节数组进行DES加密
			byte[] ciphertext = DES.encrypt(xor, key);

			// 记录MAC值的原始长度
			int macLength = mac.length;
			// 扩展MAC值长度
			mac = Arrays.copyOf(mac, macLength + ciphertext.length);
			// 将密文复制进MAC中
			System.arraycopy(ciphertext, 0, mac, macLength, ciphertext.length);
		}

		return mac;
	}
}
