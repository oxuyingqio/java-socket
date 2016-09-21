package cn.xuyingqi.socket.bio.server.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.socket.bio.server.core.ServerAppXml;
import cn.xuyingqi.socket.bio.server.core.ServerAppXml.ServletConfig;
import cn.xuyingqi.socket.bio.servlet.impl.DefaultServletConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * Servlet容器.<br>
 * 获取该实例时,Servlet已提前实例化,因此在程序中每个Servlet都是单例.
 * 
 * @author XuYQ
 *
 */
public final class ServletContainer implements cn.xuyingqi.net.server.container.ServletContainer {

	/**
	 * Servlet容器
	 */
	private static ServletContainer container;

	/**
	 * Servlet集合
	 */
	private static Map<String, Servlet> servlets = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ServletContainer() {

		// 获取Servlet配置集合
		List<ServletConfig> configs = ServerAppXml.getInstance().getServletConfigs();

		try {

			// 遍历Servlet配置集合
			for (int i = 0, length = configs.size(); i < length; i++) {

				// Servlet配置
				ServletConfig config = configs.get(i);

				// 实例化Servlet
				Servlet servlet = (Servlet) this.getClass().getClassLoader().loadClass(config.getClassName())
						.newInstance();
				// 使用默认Servlet配置加载初始化参数
				servlet.init(new DefaultServletConfig(
						config.getInitParam() == null ? new HashMap<String, String>() : config.getInitParam()));

				// 添加Servlet
				this.addServlet(config.getName(), servlet);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Servlet容器实例
	 * 
	 * @return
	 */
	public static final ServletContainer getInstance() {

		if (container == null) {
			container = new ServletContainer();
		}

		return container;
	}

	/**
	 * 添加Servlet
	 * 
	 * @param name
	 *            Servlet名称
	 * @param servlet
	 *            Servlet
	 */
	private void addServlet(String name, Servlet servlet) {

		servlets.put(name, servlet);
	}

	@Override
	public Servlet getServlet(String name) {

		return servlets.get(name);
	}

	@Override
	public Set<String> getServletNames() {

		return servlets.keySet();
	}
}
