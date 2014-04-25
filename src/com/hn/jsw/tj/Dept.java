package com.hn.jsw.tj;
import java.util.ArrayList;
import java.util.List;


public class Dept {

	private String Name;
	
	private double xh;
	
	private double edh;
	
	private double wrq;
	
	private double zr;
	
	private double cx;
	
	private double jz;
	
	private int id;
	
	private List<Dept> subs = new ArrayList<Dept>();;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getXh() {
		return xh;
	}

	public void setXh(double xh) {
		this.xh = xh;
	}

	public double getEdh() {
		return edh;
	}

	public void setEdh(double edh) {
		this.edh = edh;
	}

	public double getWrq() {
		return wrq;
	}

	public void setWrq(double wrq) {
		this.wrq = wrq;
	}

	public double getZr() {
		return zr;
	}

	public void setZr(double zr) {
		this.zr = zr;
	}

	public double getCx() {
		return cx;
	}

	public void setCx(double cx) {
		this.cx = cx;
	}

	public double getJz() {
		return jz;
	}

	public void setJz(double jz) {
		this.jz = jz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Dept> getSubs() {
		return subs;
	}

	public void setSubs(List<Dept> subs) {
		this.subs = subs;
	}

	public void addSub(Dept dept){
		subs.add(dept);
	}
	
}
