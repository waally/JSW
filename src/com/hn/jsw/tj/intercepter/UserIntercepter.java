package com.hn.jsw.tj.intercepter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hn.jsw.tj.action.LoginAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class UserIntercepter implements Interceptor{

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "";
		if (invocation.getAction() instanceof LoginAction) {
			result = invocation.invoke();
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			if (request.getSession().getAttribute("USER_NAME") != null) {
				result = invocation.invoke();
			} else {
				return Action.LOGIN;
			}
		}
		return result;
	}

}
