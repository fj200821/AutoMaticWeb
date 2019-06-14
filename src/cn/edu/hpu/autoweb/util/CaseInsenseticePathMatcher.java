package cn.edu.hpu.autoweb.util;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * 设置访问路径不区分大小写
 * 
 * @author Administrator
 * 
 */
public class CaseInsenseticePathMatcher extends AntPathMatcher {

	@Override
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		return super.doMatch(pattern.toLowerCase(), path.toLowerCase(),
				fullMatch, uriTemplateVariables);
	}

}
