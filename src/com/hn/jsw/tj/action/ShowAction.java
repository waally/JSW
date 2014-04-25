package com.hn.jsw.tj.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hn.jsw.bean.Dept;
import com.hn.jsw.util.TownsDataUtil;


public class ShowAction {
	
	private int id;
	
	private Map<String,Object> result = new HashMap<String,Object>();
	
	private String sort;
	
	private String showTSort = "false";
	
	private String picType = "column";
	
    public String execute()  {
    	Dept dept = getById(id);
    	getMap(dept);
    	System.out.println("查看"+dept.getName()+"得分情况");
    	return "SUCCESS";
    }
    
    
    private List<Dept> sortSubs(Dept dept){
    	List<Dept> depts = new ArrayList<Dept>();
    	final String key = "field"+(TownsDataUtil.fileds.size()-1);
    	for(Dept sub:dept.getSubs()){
    		depts.add(sub);
    	}
    	if(sort==null){
    		return depts;
    	}else if(sort.equals("asc")){
    		Comparator<Dept> compar = new  Comparator<Dept>(){
    			@Override
    			public int compare(Dept o1, Dept o2) {
    				double result = o1.getFields().get(key)-o2.getFields().get(key);
    				if(result>0){
    					return 1;
    				}else{
    					return -1;
    				}
    			}
    		};
    		Collections.sort(depts,compar);
    	}else if(sort.equals("desc")){
    		Comparator<Dept> compar = new  Comparator<Dept>(){
    			@Override
    			public int compare(Dept o1, Dept o2) {
    				double result = o1.getFields().get(key)-o2.getFields().get(key);
    				if(result>0){
    					return -1;
    				}else{
    					return 1;
    				}
    			}
    		};
    		Collections.sort(depts,compar);
    	}
    	return depts;
    }
    /**
     * 获取所有要展示的子机构的名称或者加上排名。
     * @param subs
     * @return
     */
    private String[] getTitleName(List<Dept> subs){
    	String[] name = new String[subs.size()];
    	for(int i = 0;i<subs.size();i++){
    		if(showTSort.equals("true")){
    			int pm = subs.get(i).getSorts().get("field"+(TownsDataUtil.fileds.size()-1));
    			if(picType.equals("column")){
    				name[i] = subs.get(i).getName()+"<br>("+pm+")";
    			}else{
    				name[i] = subs.get(i).getName()+"("+pm+")";
    			}
    		}else{
    			name[i] = subs.get(i).getName();
    		}
		}
    	return name;
    }
    /**
     * 获取要返回的展示数据对象。
     * @param subs
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map[] getSeries(List<Dept> subs){
    	Map[] series = new HashMap[TownsDataUtil.fileds.size()];
    	if( result.get("zavg")!=null){
    		series = new HashMap[TownsDataUtil.fileds.size()+1];
    		Map avg = new HashMap();
    		avg.put("type", "line");
    		avg.put("name", "本乡镇平均值");
    		avg.put("dashStyle", "dash");
    		Map[] ds = new HashMap[subs.size()];
    		for(int i=0;i<subs.size();i++){
    			ds[i]=new HashMap();
    			ds[i].put("x", i);
    			ds[i].put("y", result.get("zavg"));
    		}
    		avg.put("data", ds);
    		series[TownsDataUtil.fileds.size()] = avg;
    	}
    	if( result.get("xavg")!=null){
    		Map avg = new HashMap();
    		avg.put("type", "line");
    		avg.put("name", "县平均值");
    		avg.put("dashStyle", "dash");
    		Map[] ds = new HashMap[subs.size()];
       		for(int i=0;i<subs.size();i++){
    			ds[i]=new HashMap();
    			ds[i].put("x", i);
    			ds[i].put("y", result.get("xavg"));
    		}
    		avg.put("data", ds);
    		series[TownsDataUtil.fileds.size()-1] = avg;
    	}
    	double[] td = new double[subs.size()];
    	for(int i = 0;i<subs.size();i++){
    		td[i] = ew(subs.get(i).getFields().get("field"+(TownsDataUtil.fileds.size()-1)));
		}
    	result.put("sts", td);
    	for(int j = 0;j<TownsDataUtil.fileds.size()-1;j++){
    		series[j] = new HashMap();
    		series[j].put("name", TownsDataUtil.fileds.get("field"+j));
    		series[j].put("type", picType);
    		Map[] vs = new HashMap[subs.size()];
    		for(int i = 0;i<subs.size();i++){
    			vs[i] = new HashMap();
    			vs[i].put("y",ew(subs.get(i).getFields().get("field"+j)));
    			vs[i].put("pm",ew(subs.get(i).getSorts().get("field"+j)));
    		}
    		series[j].put("data", vs);
    	}
    	return series;
    }
    /**
     * 构造要返回的json对象集合。
     * @param dept
     */
	private void getMap(Dept dept){
    	List<Dept> subs = sortSubs(dept);
    	result.put("name", getTitleName(subs));
    	result.put("series", getSeries(subs));
    }
	/**
	 * 根据id获取对应的机构，如果id为0，则表示查询的是怀宁县的信息。
	 * 并且根据情况添加上县平均值和乡镇平均值。
	 * @param id
	 * @return
	 */
    private Dept getById(int id){
    	if(id==0){
    		result.put("title", TownsDataUtil.hn.getName()+TownsDataUtil.titleText);
    		result.put("xavg", ew(TownsDataUtil.hn.getFields().get("field"+(TownsDataUtil.fileds.size()-1))));
    		return TownsDataUtil.hn;
    	}else{
    		for(Dept dept:TownsDataUtil.hn.getSubs()){
    			if(dept.getId()==id){
    				result.put("title", dept.getName()+TownsDataUtil.titleText);
    				result.put("xavg", ew(TownsDataUtil.hn.getFields().get("field"+(TownsDataUtil.fileds.size()-1))));
    				result.put("zavg", ew(dept.getFields().get("field"+(TownsDataUtil.fileds.size()-1))));
    				return dept;
    			}
    		}
    	}
    	return null;
    }
    private double ew(double d){
    	int i = (int) Math.round((d*100));
    	return i/100.0;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getShowTSort() {
		return showTSort;
	}

	public void setShowTSort(String showTSort) {
		this.showTSort = showTSort;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}
	
}
