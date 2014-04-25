package com.hn.jsw.tj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShowAction {
	
	private int id;
	
	private List<Dept> list;
	
	private Map<String,String> map = new HashMap<String,String>();
	
    public String execute()  {
    	Dept dept = getById(id);
    	getMap(dept);
    	System.out.println(dept.getName());
    	return "SUCCESS";
    }
/**
 * private double xh;
	
	private double edh;
	
	private double wrq;
	
	private double zr;
	
	private double cx;
	
	private double jz;
 * @param dept
 */
    private void getMap(Dept dept){
    	for(Dept d:dept.getSubs()){
    		if(map.get("name")==null){
    			map.put("name","'"+d.getName()+"',");
    		}else{
    			map.put("name", map.get("name")+"'"+d.getName()+"',");
    		}
    		if( map.get("xh")==null){
    			map.put("xh", d.getXh()+",");
    		}else{
    			map.put("xh", map.get("xh")+d.getXh()+",");
    		}
    		if( map.get("edh")==null){
    			map.put("edh", d.getEdh()+",");
    		}else{
    			map.put("edh", map.get("edh")+d.getEdh()+",");
    		}
    		if( map.get("wrq")==null){
    			map.put("wrq", d.getWrq()+",");
    		}else{
    			map.put("wrq", map.get("wrq")+d.getWrq()+",");
    		}
    		if( map.get("zr")==null){
    			map.put("zr", d.getZr()+",");
    		}else{
    			map.put("zr", map.get("zr")+d.getZr()+",");
    		}
    		if( map.get("cx")==null){
    			map.put("cx", d.getCx()+",");
    		}else{
    			map.put("cx", map.get("cx")+d.getCx()+",");
    		}
    		if( map.get("jz")==null){
    			map.put("jz", d.getJz()+",");
    		}else{
    			map.put("jz", map.get("jz")+d.getJz()+",");
    		}
    	}
    }
    private Dept getById(int id){
    	if(id==0){
    		map.put("title", "怀宁县总体情况");
    		return ExcelListener.hn;
    	}else{
    		for(Dept dept:ExcelListener.hn.getSubs()){
    			if(dept.getId()==id){
    				map.put("title", dept.getName()+"情况");
    				return dept;
    			}
    		}
    	}
    	return null;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Dept> getList() {
		return list;
	}

	public void setList(List<Dept> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}


}
