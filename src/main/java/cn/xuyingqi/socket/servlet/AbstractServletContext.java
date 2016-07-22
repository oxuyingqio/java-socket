package cn.xuyingqi.socket.servlet;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.util.util.MapFactory;

/**
 * 抽象公共Servlet上下文
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractServletContext implements ServletContext {

	/**
	 * 属性
	 */
	private Map<String, Object> attributes = MapFactory.newInstance();

	@Override
	public void setAttribute(String name, Object object) {
		attributes.put(name, object);
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}
}
