package cn.xuyingqi.socket;

import org.apache.log4j.Logger;

/**
 * 服务器
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

		config = ServerConfig.newInstance();
		System.out.println(config.getServerPreferences().getHostName());
		System.out.println(config.getServerPreferences().getPort());
		System.out.println(config.getServerPreferences().getPerformancePreferences().getConnectionTime());
		System.out.println(config.getServerPreferences().getPerformancePreferences().getLatency());
		System.out.println(config.getServerPreferences().getPerformancePreferences().getBandwidth());
	}
}
