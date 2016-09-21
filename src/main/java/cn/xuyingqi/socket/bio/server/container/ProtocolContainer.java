package cn.xuyingqi.socket.bio.server.container;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.xuyingqi.socket.bio.protocol.Protocol;
import cn.xuyingqi.socket.bio.server.core.ServerXml;
import cn.xuyingqi.socket.bio.server.core.ServerXml.ProtocolConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 协议容器.<br>
 * 获取该实例时,Protocol已提前实例化,因此在程序中每个Protocol都是单例.
 * 
 * @author XuYQ
 *
 */
public final class ProtocolContainer implements cn.xuyingqi.net.server.container.ProtocolContainer {

	/**
	 * 协议容器
	 */
	private static ProtocolContainer container;

	/**
	 * 协议类对象集合
	 */
	private static Map<String, Protocol> protocolClasses = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ProtocolContainer() {

		// 获取协议配置集合
		List<ProtocolConfig> configs = ServerXml.getInstance().getProtocolConfigs();

		try {

			// 遍历协议配置集合
			for (int i = 0, length = configs.size(); i < length; i++) {

				// 获取协议类对象
				Protocol protocol = (Protocol) this.getClass().getClassLoader().loadClass(configs.get(i).getClassName())
						.newInstance();

				// 添加协议类对象
				this.addProtocol(configs.get(i).getName(), protocol);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取协议容器实例
	 * 
	 * @return
	 */
	public static final ProtocolContainer getInstance() {

		if (container == null) {
			container = new ProtocolContainer();
		}

		return container;
	}

	/**
	 * 添加协议类对象
	 * 
	 * @param name
	 *            协议名称
	 * @param protocol
	 *            协议类对象
	 */
	private void addProtocol(String name, Protocol protocol) {

		protocolClasses.put(name, protocol);
	}

	@Override
	public Protocol getProtocol(String name) {

		return protocolClasses.get(name);
	}

	@Override
	public Set<String> getProtocolNames() {

		return protocolClasses.keySet();
	}
}
