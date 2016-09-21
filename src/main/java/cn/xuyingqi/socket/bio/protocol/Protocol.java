package cn.xuyingqi.socket.bio.protocol;

/**
 * 协议
 * 
 * @author XuYQ
 *
 */
public interface Protocol extends cn.xuyingqi.net.protocol.Protocol {

	/**
	 * 获取编码器
	 * 
	 * @return
	 */
	public Encoder getEncoder();

	/**
	 * 获取解码器
	 * 
	 * @return
	 */
	public Decoder getDecoder();
}
