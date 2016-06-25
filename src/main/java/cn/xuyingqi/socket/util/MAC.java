package cn.xuyingqi.socket.util;

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

		// 获取对应块数
		int blockLength = macData.length / 8;
		// 遍历每一块
		for (int i = 0; i < blockLength; i++) {

			// 每一块与初始向量进行异或,并将结果赋予初始向量
			vector = ByteUtils.xor(ArrayUtils.subarray(macData, i * 8, (i + 1) * 8), vector);
			// 对初始向量进行DES加密,同时仅取前8位
			vector = ArrayUtils.subarray(DES.encrypt(vector, key), 0, 8);
		}

		return vector;
	}
}
