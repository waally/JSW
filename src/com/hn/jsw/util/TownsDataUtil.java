package com.hn.jsw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hn.jsw.bean.Dept;

public class TownsDataUtil {
	/**
	 * name名称,subs子元素
	 */
	public static Dept hn;
	public static List<Dept> towms = new ArrayList<Dept>();
	public static List<Dept> villages = new ArrayList<Dept>();
	
	public static String titleText = "人口计生村为主工作绩效月监测示意图";
	
	public static Map<String,Object> fileds = new HashMap<String,Object>();

	public static void main(String[] args) throws Exception {
		File file = new File("src/a.xls");
		try {
			initDate(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initDate(File file) throws Exception{
		towms.clear();
		villages.clear();
		fileds.clear();
		titleText="";
		System.out.println("初始化excel文件"+file.getAbsolutePath());
		initData(file);
        System.out.println("初始化excel文件完成");
        putRowMap(fileds,"总分");
        calculateTotle();
        System.out.println("计算镇乡村总得分完成");
        calculateSort();
        System.out.println("计算镇乡村排名完成");
        printDept(hn);
	}
	
	private static void calculateSort() {
		setSort(towms);
		setSort(villages);
	}

	private static void setSort(List<Dept> depts) {
		Dept head = depts.get(0);
		for(final String key :head.getFields().keySet()){
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
			for(int i=0;i<depts.size();i++){
				depts.get(i).getSorts().put(key, i+1);
			}
		}
	}

	private static void calculateTotle() {
		double ttotal = 0;
		for(double score:hn.getFields().values()){
			ttotal+=score;
		}
		hn.addField(ttotal);
		for(Dept d1:hn.getSubs()){
			ttotal = 0;
			for(double score:d1.getFields().values()){
				ttotal+=score;
			}
			d1.addField(ttotal);
			towms.add(d1);
			 for(Dept d2:d1.getSubs()){
				 double total = 0;
				 for(double score:d2.getFields().values()){
					 total+=score;
				 }
				 d2.addField(total);
				 villages.add(d2);
			 }
	     }
	}

	private static void initData(File file) throws Exception{
		InputStream inp = new FileInputStream(file); 
        Workbook wb = WorkbookFactory.create(inp); 
        Sheet sheet = wb.getSheetAt(0);
        Dept zx = new Dept();
        int rowNum = 0;
        for (Row row : sheet) { 
        	rowNum++;
        	if(rowNum==1){
        		for(Cell cell:row){
        			if(cell.getStringCellValue()!=null&&cell.getStringCellValue().length()!=0){
        				titleText+=cell.getStringCellValue();
        			}
        		}
        	}
        	if(rowNum==2){
        		int cellNum = 0;
        		for(Cell cell:row){
        			cellNum++;
        			if(cell.getStringCellValue()!=null&&cell.getStringCellValue().length()!=0){
        				if(cellNum!=1){
        					putRowMap(fileds,cell.getStringCellValue());
        				}
        			}
        		}
        		cellNum = 0;
        	}else if(rowNum>2){
        		if(row.getCell(0).toString()==null||row.getCell(0).toString().length()==0){
        			break;
        		}else{
        			Dept temp = new Dept();
        			int cellNum = 0;
        			for(Cell cell:row){
        				cellNum++;
        				if(cell.toString()!=null&&cell.toString().length()!=0){
        					if(cellNum==1){
        						temp.setName(cell.getStringCellValue());
        					}else{
        						if(cell.getCellType()!=0){
        							temp.addField(0.0);
        						}else{
        							temp.addField(cell.getNumericCellValue());
        						}
        					}
        				}
        			}
        			cellNum = 0;
        			if(row.getCell(0).toString().endsWith("县")){
        				hn = temp;
        				hn.setId(0);
        			}else if(row.getCell(0).toString().endsWith("镇")||row.getCell(0).toString().endsWith("乡")){
        				zx = temp;
        				hn.addSub(zx);
        			}else{
        				zx.addSub(temp);
        			}
        		}
        		
        	}
        } 
        inp.close(); 
	}
	
	private static void calculate(){
		 for(Dept d1:hn.getSubs()){
			 for(int i =0;i<fileds.size();i++){
				 d1.getFields().put("field"+i,0.0);
				 for(Dept d2:d1.getSubs()){
					 d1.getFields().put("field"+i,d1.getFields().get("field"+i)+d2.getFields().get("field"+i));
				 }
				 d1.getFields().put("field"+i,d1.getFields().get("field"+i)/d1.getSubs().size());
			 }
	     }
		 for(int i =0;i<fileds.size();i++){
			 hn.getFields().put("field"+i,0.0);
			 for(Dept d2:hn.getSubs()){
				 hn.getFields().put("field"+i,hn.getFields().get("field"+i)+d2.getFields().get("field"+i));
			 }
			 hn.getFields().put("field"+i,hn.getFields().get("field"+i)/hn.getSubs().size());
		 }
	}
	
	private static void putRowMap(Map<String,Object> map,String value){
		map.put("field"+map.size(), value);
	}
	
	private static void printDept(Dept dept){
		System.out.print(dept.getName()+"\t"+dept.getSorts().get("field7")+"\t");
		for(int i=0;i<dept.getFields().keySet().size();i++){
			System.out.print("field"+i+":"+"\t"+(int)(dept.getFields().get("field"+i)*100)/100.0+"\t");
		}
		System.out.println("");
		if(dept.getSubs().size()!=0){
			for(Dept sub:dept.getSubs()){
				printDept(sub);
			}
		}
	}
	
}
