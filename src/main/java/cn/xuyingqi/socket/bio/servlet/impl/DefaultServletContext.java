package cn.xuyingqi.socket.bio.servlet.impl;

import java.util.Set;

import cn.xuyingqi.net.servlet.impl.AbstractServletContext;
import cn.xuyingqi.util.util.SetFactory;

/**
 * 默认的Servlet上下文
 * 
 * @author XuYQ
 *
 */
public class DefaultServletContext extends AbstractServletContext {

	@Override
	public Set<String> getInitParamterNames() {

		// 未实现,不影响使用

		return SetFactory.newInstance();
	}

	@Override
	public Object getInitParameter(String name) {

		// 未实现,不影响使用

		return null;
	}
}
