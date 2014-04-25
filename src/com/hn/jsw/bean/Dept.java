package com.hn.jsw.bean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dept {

	private static int cid = 1;
	
	private String name;
	
	private int id;
	
	private Map<String,Double> fields = new HashMap<String,Double>();
	
	private Map<String,Integer> sorts = new HashMap<String,Integer>();
	
	private List<Dept> subs = new ArrayList<Dept>();

	public Dept(){
		id = cid++;
	}
	public Dept(int id,String name){
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Double> getFields() {
		return fields;
	}

	public void setFields(Map<String, Double> fields) {
		this.fields = fields;
	}

	public Map<String, Integer> getSorts() {
		return sorts;
	}

	public void setSorts(Map<String, Integer> sorts) {
		this.sorts = sorts;
	}

	public List<Dept> getSubs() {
		return subs;
	}

	public void setSubs(List<Dept> subs) {
		this.subs = subs;
	};

	public void addSub(Dept sub){
		subs.add(sub);
	}
	
	public void addField(double value){
		fields.put("field"+fields.size(), value);
	}
}
