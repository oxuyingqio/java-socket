package cn.xuyingqi.socket;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * 服务器配置
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "socket-app")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerConfig {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ServerConfig.class);

	/**
	 * 服务器配置文件的默认位置
	 */
	private static final String DEFAULT_SERVER_CONFIG_FILE = "socket.xml";

	/**
	 * 服务器参数
	 */
	@XmlElement(name = "server-preferences")
	private ServerPreferences serverPreferences = new ServerPreferences();

	/**
	 * 获取服务器参数
	 * 
	 * @return
	 */
	public ServerPreferences getServerPreferences() {
		return serverPreferences;
	}

	/**
	 * 获取服务器配置
	 * 
	 * @return
	 */
	public static ServerConfig newInstance() {

		try {

			JAXBContext jc = JAXBContext.newInstance(ServerConfig.class);
			Unmarshaller u = jc.createUnmarshaller();
			return (ServerConfig) u.unmarshal(
					new File(ServerConfig.class.getClassLoader().getResource(DEFAULT_SERVER_CONFIG_FILE).getPath()));

		} catch (JAXBException e) {

			// 打印日志
			logger.error("服务器配置文件解析异常");
		}

		return new ServerConfig();
	}

	/**
	 * 服务器配置:服务器参数
	 * 
	 * @author XuYQ
	 *
	 */
	static class ServerPreferences {

		/**
		 * 默认主机名
		 */
		private static final String DEFAULT_HOST_NAME = "127.0.0.1";
		/**
		 * 默认端口号
		 */
		private static final int DEFAULT_PORT = 60000;

		/**
		 * 主机名
		 */
		@XmlElement(name = "host-name")
		private String hostName = DEFAULT_HOST_NAME;
		/**
		 * 端口号
		 */
		@XmlElement(name = "port")
		private int port = DEFAULT_PORT;
		/**
		 * 性能参数
		 */
		@XmlElement(name = "performance-preferences")
		private PerformancePreferences performancePreferences;

		/**
		 * 获取主机名
		 * 
		 * @return
		 */
		public String getHostName() {
			return hostName;
		}

		/**
		 * 获取端口号
		 * 
		 * @return
		 */
		public int getPort() {
			return port;
		}

		/**
		 * 获取性能参数
		 * 
		 * @return
		 */
		public PerformancePreferences getPerformancePreferences() {
			return performancePreferences;
		}

		/**
		 * 服务器参数:性能参数
		 * 
		 * @author XuYQ
		 *
		 */
		static class PerformancePreferences {

			/**
			 * 连接时间
			 */
			@XmlElement(name = "connection-time")
			private int connectionTime;
			/**
			 * 延迟
			 */
			@XmlElement(name = "latency")
			private int latency;
			/**
			 * 带宽
			 */
			@XmlElement(name = "bandwidth")
			private int bandwidth;

			/**
			 * 获取连接时间
			 * 
			 * @return
			 */
			public int getConnectionTime() {
				return connectionTime;
			}

			/**
			 * 获取延迟
			 * 
			 * @return
			 */
			public int getLatency() {
				return latency;
			}

			/**
			 * 获取带宽
			 * 
			 * @return
			 */
			public int getBandwidth() {
				return bandwidth;
			}
		}
	}
}
