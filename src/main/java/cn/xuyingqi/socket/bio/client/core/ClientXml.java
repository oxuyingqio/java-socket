package cn.xuyingqi.socket.bio.client.core;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * client.xml配置
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ClientXml {

	/**
	 * client.xml配置
	 */
	private static ClientXml clientXml;

	/**
	 * 协议配置集合
	 */
	@XmlElements(value = { @XmlElement(name = "protocol", type = ProtocolConfig.class) })
	private List<ProtocolConfig> protocolConfigs;

	/**
	 * 服务配置
	 */
	@XmlElement(name = "service")
	private ServiceConfig serviceConfig;

	/**
	 * 私有构造方法
	 */
	private ClientXml() {

	}

	/**
	 * 获取client.xml配置实例
	 * 
	 * @return
	 */
	public static final ClientXml getInstance() {

		if (clientXml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ClientXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				clientXml = (ClientXml) u.unmarshal(
						new File(ClientXml.class.getClassLoader().getResource(Constant.CLIENT_CONFIG_FILE).getPath()));

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return clientXml;
	}

	/**
	 * 获取协议配置集合
	 * 
	 * @return
	 */
	public List<ProtocolConfig> getProtocolConfigs() {
		return protocolConfigs;
	}

	/**
	 * 获取服务配置
	 * 
	 * @return
	 */
	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	/**
	 * 协议配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class ProtocolConfig {

		/**
		 * 协议名称
		 */
		@XmlAttribute(name = "name")
		private String name;

		/**
		 * 协议类路径
		 */
		@XmlAttribute(name = "className")
		private String className;

		/**
		 * 获取协议名称
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * 获取协议类路径
		 * 
		 * @return
		 */
		public String getClassName() {
			return className;
		}
	}

	/**
	 * 服务配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class ServiceConfig {

		/**
		 * 连接器配置集合
		 */
		@XmlElements(value = { @XmlElement(name = "connector", type = ConnectorConfig.class) })
		private List<ConnectorConfig> connectorConfigs;

		/**
		 * 获取连接器配置
		 * 
		 * @return
		 */
		public List<ConnectorConfig> getConnectorConfig() {
			return connectorConfigs;
		}

		/**
		 * 连接器配置
		 * 
		 * @author XuYQ
		 *
		 */
		public static final class ConnectorConfig {

			/**
			 * 连接器名称
			 */
			@XmlAttribute(name = "name")
			private String name;

			/**
			 * 协议名称
			 */
			@XmlAttribute(name = "protocol")
			private String protocol;

			/**
			 * 主机地址
			 */
			@XmlAttribute(name = "host")
			private String host;

			/**
			 * 端口号
			 */
			@XmlAttribute(name = "port")
			private int port;

			/**
			 * 超时时间
			 */
			@XmlAttribute(name = "timeout")
			private int timeout;

			public String getName() {
				return name;
			}

			public String getProtocol() {
				return protocol;
			}

			public String getHost() {
				return host;
			}

			public int getPort() {
				return port;
			}

			public int getTimeout() {
				return timeout;
			}
		}
	}
}
