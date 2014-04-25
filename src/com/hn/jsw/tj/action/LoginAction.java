package com.hn.jsw.tj.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	
	private String name;
	
	private String passwd;
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute()  {
		if(name==null||name.length()==0||passwd==null||passwd.length()==0){
			return "failed";
		}
		if(name.equals("wangli")&&passwd.equals("123456")){
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        session.put("USER_NAME", name);
			return "admin";
		}
		if(name.equals("wqx")&&passwd.equals("123456")){
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        session.put("USER_NAME", name);
			return "guest";
		}
		return "failed";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
