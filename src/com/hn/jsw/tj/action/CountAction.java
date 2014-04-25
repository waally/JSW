package com.hn.jsw.tj.action;

import java.util.ArrayList;
import java.util.List;

import com.hn.jsw.bean.Dept;
import com.hn.jsw.util.TownsDataUtil;

public class CountAction {

	private List<Dept> depts;
	
	public String execute()  {
		depts = new ArrayList<Dept>();
		depts.add(TownsDataUtil.hn);
		depts.addAll(TownsDataUtil.hn.getSubs());
		return "SUCCESS";
    }
	
	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}
	
}
