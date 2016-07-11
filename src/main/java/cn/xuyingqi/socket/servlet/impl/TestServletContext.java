package cn.xuyingqi.socket.servlet.impl;

import java.util.Map;

import cn.xuyingqi.socket.servlet.ServletContext;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 测试Servlet上下文
 * 
 * @author XuYQ
 *
 */
public class TestServletContext implements ServletContext {

	/**
	 * 属性
	 */
	private Map<String, Object> attribute = MapFactory.newInstance();

	@Override
	public void setAttribute(String name, Object object) {

		this.attribute.put(name, object);
	}

	@Override
	public Object getAttribute(String name) {

		return this.attribute.get(name);
	}
}
