package cn.xuyingqi.socket;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 服务器1
 * 
 * @author XuYQ
 *
 */
public class Server {

	// 日志
	private Logger logger = Logger.getLogger(Server.class);

	// 服务器配置
	private ServerConfig config;

	/**
	 * 服务器
	 */
	public Server() {

		this.init();
	}

	/**
	 * 初始化服务器
	 */
	private void init() {

		config = new ServerConfig();
	}

	/**
	 * 服务器配置
	 * 
	 * @author XuYQ
	 *
	 */
	private class ServerConfig {

		// 默认服务器配置文件位置
		private static final String SERVER_CONFIG_FILE_NAME = "socket.xml";

		/**
		 * 服务器配置
		 */
		public ServerConfig() {

			// XML文件读取
			SAXReader reader = new SAXReader();
			// 指定文件
			File file = new File(ServerConfig.class.getClassLoader().getResource(SERVER_CONFIG_FILE_NAME).getPath());
			try {
				// 读取配置文件
				Document document = reader.read(file);
				// 获取根
				Element root = document.getRootElement();
				// 获取子集
				@SuppressWarnings("unchecked")
				List<Element> children = root.elements();
				// 遍历子集
				for (int i = 0, length = children.size(); i < length; i++) {

					// 子
					Element child = children.get(i);

				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}
