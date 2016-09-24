package cn.xuyingqi.socket.bio.client.core;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * client.app.xml配置
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "client-app")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ClientAppXml {

	/**
	 * client.app.xml配置
	 */
	private static ClientAppXml clientAppXml;

	/**
	 * Servlet配置集合
	 */
	@XmlElements(value = { @XmlElement(name = "servlet", type = ServletConfig.class) })
	private List<ServletConfig> servletConfigs;

	/**
	 * 私有构造方法
	 */
	private ClientAppXml() {

	}

	/**
	 * 获取client.app.xml配置实例
	 * 
	 * @return
	 */
	public static final ClientAppXml getInstance() {

		if (clientAppXml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ClientAppXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				clientAppXml = (ClientAppXml) u.unmarshal(new File(
						ClientAppXml.class.getClassLoader().getResource(Constant.CLIENT_APP_CONFIG_FILE).getPath()));

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return clientAppXml;
	}

	/**
	 * 获取Servlet配置集合
	 * 
	 * @return
	 */
	public List<ServletConfig> getServletConfigs() {
		return servletConfigs;
	}

	/**
	 * Servlet配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class ServletConfig {

		/**
		 * Servlet名称
		 */
		@XmlAttribute(name = "name")
		private String name;

		/**
		 * 客户端名称
		 */
		@XmlAttribute(name = "client")
		private String client;

		/**
		 * Servlet类路径
		 */
		@XmlAttribute(name = "className")
		private String className;

		/**
		 * Servlet初始化参数
		 */
		@XmlElementWrapper(name = "init-param")
		private Map<String, String> initParam;

		/**
		 * 获取Servlet名称
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * 获取客户端名称
		 * 
		 * @return
		 */
		public String getClient() {
			return client;
		}

		/**
		 * 获取Servlet类路径
		 * 
		 * @return
		 */
		public String getClassName() {
			return className;
		}

		/**
		 * 获取Servlet初始化参数
		 * 
		 * @return
		 */
		public Map<String, String> getInitParam() {
			return initParam;
		}
	}
}
